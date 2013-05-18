/*
 * IBusIntentsService.java
 * Copyright (C) 2013 David Barksdale <amatus.amongus@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package name.amatus.ibusintents;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class IBusIntentsService extends IntentService
{
  private static final String Name = "IBusIntentsService";
  private static final UUID UUID16ServiceClassSerialPort =
    UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

  public IBusIntentsService() {
    super(Name);
  }

  @Override
  protected void onHandleIntent(Intent intent)
  {
    Log.d(Name, "onHandleIntent(intent=" + intent.toString() + ")");
    if (!intent.hasExtra("device")) {
      Log.w(Name, "Intent has no device");
      return;
    }
    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
    if (adapter == null) {
      Log.w(Name, "Unable to get default bluetooth adapter");
      return;
    }
    BluetoothDevice device =
      adapter.getRemoteDevice(intent.getStringExtra("device"));
    BluetoothSocket socket;
    InputStream stream;
    try {
      socket =
        device.createRfcommSocketToServiceRecord(UUID16ServiceClassSerialPort);
      socket.connect();
      stream = socket.getInputStream();
      while (true) {
        byte[] buffer = new byte[256];
        int length = stream.read(buffer);
        if (-1 == length) {
          Log.w(Name, "Read failed");
          break;
        }
        String hex = new String();
        for (int i = 0; i < length; ++i) {
          hex += String.format(" %02X", buffer[i]);
        }
        Log.d(Name, "I-Bus says:" + hex);
      }
      socket.close();
    } catch (IOException exception) {
      Log.w(Name, "Exception: " + exception.toString());
      return;
    }
  }

  @Override
  public void onDestroy()
  {
    Log.d(Name, "onDestroy");
    super.onDestroy();
  }
}
/* vim: set expandtab ts=2 sw=2: */
