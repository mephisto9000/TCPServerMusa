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
    
    
    
    public SpaceMarine deserialize(InputStream in) throws IOException {        
        
                     
		String name = readString(in);
                
                System.out.println("name == "+name);
                
		String chapter = readString(in);
                
                System.out.println("chapter == "+ chapter);
                
                int kills = Integer.parseInt(readString(in));
                
                System.out.println("kills == " + kills);
                
                SMRank rank = SMRank.valueOf(readString(in));
                
                System.out.println("rank == " + rank.name());
                
                SMLoyalty loyalty = SMLoyalty.valueOf(readString(in));
                System.out.println("loyalty == " + loyalty.name());
                
                SMStatus status = SMStatus.valueOf(readString(in));
                System.out.println("status == "+status.name());
                //public SpaceMarine(String name, String chapter, int kills, SMRank rank, SMLoyalty loyalty, int damage)
                int damage = Integer.parseInt(readString(in));
                
                
                System.out.println("damage == " + damage);
                
                SpaceMarine spacemarine = new SpaceMarine(name, chapter, kills, rank, loyalty, damage);

                int b = in.available();
                in.skip(b);
		
		return spacemarine;
	
    }
    
    private String readString(InputStream in) throws IOException
    {
        StringBuilder sb   = new StringBuilder();

        int val;
        while(in.available()>0)
        {
            
            //in.
            val = in.read();
            
            if (((char)val)==';' || val == -1)
                break;
           
            sb.append((char)val);
        }

        //System.out.println("string == " +sb.toString());
	return sb.toString();
    }

    
    
    
}
