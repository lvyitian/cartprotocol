/*
 * Copyright (c) liachmodded
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.liachmodded.cartprotocol.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;

public final class EventTools {

  @SuppressWarnings("unchecked") // TODO Update and remove cast/generics when fab api updates
  public static <H extends PacketListener, P extends Packet<? super H>> Event<PacketCallback<H, P>> event() {
    return EventFactory.createArrayBacked((Class<PacketCallback<H, P>>) (Class<?>) PacketCallback.class, handlers -> (packet, listener) -> {
      for (PacketCallback<H, P> handler : handlers) {
        if (handler.handle(packet, listener)) {
          return true;
        }
      }
      return false;
    });
  }

  private EventTools() {}
}
