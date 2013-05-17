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
import android.content.Intent;
import android.util.Log;
import java.util.UUID;

public class IBusIntentsService extends IntentService
{
  private static final String Name = "IBusIntentsService";
  private static final UUID BluetoothSDPUUID16ServiceClassSerialPort =
    UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

  public IBusIntentsService() {
    super(Name);
  }

  @Override
  protected void onHandleIntent(Intent intent)
  {
    Log.d(Name, "onHandleIntent(intent=" + intent.toString() + ")");
  }

  @Override
  public void onDestroy()
  {
    Log.d(Name, "onDestroy");
    super.onDestroy();
  }
}
/* vim: set expandtab ts=2 sw=2: */
