/*
 * Copyright 2015 SpringIO.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.musa.payload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.lang.enums.EnumUtils;
import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.Serializer;

/**
 *
 * @author mephisto9000
 */
public class MechanicusTeleportReceiver implements Deserializer<SpaceMarine>{

    public void serialize(SpaceMarine spaceMarine, OutputStream out) throws IOException {
                
                       
                /*
                disassembling the space marine...
                */
                
                byte[] nameParticles = spaceMarine.getName().getBytes();
		out.write(nameParticles);

		byte[] chapterParticles = spaceMarine.getChapter().getBytes();
		out.write(chapterParticles);

		byte[] killsParticles = Integer.toString(spaceMarine.getKills()).getBytes();                
		out.write(killsParticles);
                
                byte[] rankParticles = spaceMarine.getRank().name().getBytes();
                out.write(rankParticles);
                
                byte[] loyaltyParticles = spaceMarine.getLoyalty().name().getBytes();
                out.write(loyaltyParticles);
                
                byte[] statusParticles = spaceMarine.getStatus().name().getBytes();
                out.write(statusParticles);
                
                               		
		out.flush();
        
     
    }

    public SpaceMarine deserialize(InputStream in) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
      
		String name = readString(in);
		String chapter = readString(in);
                int kills = Integer.parseInt(readString(in));
                SMRank rank = SMRank.valueOf(readString(in));
                SMLoyalty loyalty = SMLoyalty.valueOf(readString(in));
                SMStatus status = SMStatus.valueOf(readString(in));
                //public SpaceMarine(String name, String chapter, int kills, SMRank rank, SMLoyalty loyalty, int damage)
                SpaceMarine marine = new SpaceMarine(name, chapter, kills, rank, loyalty, damage);

		CustomOrder order = new CustomOrder(orderNumber, senderName);
		String message = parseMessage(inputStream);
		order.setMessage(message);
		return order;
	
    }
    
    private String readString(InputStream in) throws IOException
    {
        StringBuilder sb = new StringBuilder();

        int val;
        while(true)
        {
            val = in.read();
            
            if (((char)val)==';')
                break;
            
            sb.append(val);
        }
        
	return sb.toString();
    }
            
    
    
    
}
