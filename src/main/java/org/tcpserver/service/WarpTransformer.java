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
package org.tcpserver.service;

import java.util.LinkedList;
import java.util.List;
import org.musa.payload.SMLoyalty;
import org.musa.payload.SpaceMarine;
import org.springframework.cglib.core.Transformer;

/**
 *
 * @author mephisto9000
 */




public class WarpTransformer implements Transformer{
    
    static List<SpaceMarine> loyalists;
    static List<SpaceMarine> traitors;
    static SMLoyalty lastAction ;
    
    static {
        loyalists = new LinkedList<SpaceMarine>();
        traitors = new LinkedList<SpaceMarine>();
    }
    


    public Object transform(Object o) {
                
        return teleportSpacemarine((SpaceMarine) o);                        
    }
    
    public String teleportSpacemarine(SpaceMarine marine) {
                      
                System.out.println(marine.getName()+" is now in Eye of Terror");
                
                boolean isLoyal = false;
                SpaceMarine enemy = null;
                StringBuffer result = new StringBuffer();
                List<SpaceMarine> marineList;
                List<SpaceMarine> enemyList;
                
                
                if (marine.getLoyalty() == SMLoyalty.Loyalist)
                {
                    //isLoyal = true;
                    loyalists.add(marine);
                    isLoyal = true;
                    
                    marineList = loyalists;
                    enemyList = traitors;
                    
                    if (traitors.size() > 0)
                        enemy = traitors.get(0);
                                                            
                }
                else
                {
                    traitors.add(marine);
                    
                    marineList = traitors;
                    enemyList = loyalists;
                    
                    if (loyalists.size() > 0)
                        enemy = loyalists.get(0);
                }
                
                if (enemy == null)   
                {
                    result.append("noone hurt");
                    return result.toString();
                }
                
                while(true)
                {
                    int damage = enemy.getDamage();
                    
                    marine.acceptDamage(damage);
                    
                    if (marine.getHealth() > 0)
                    {
                        damage = marine.getDamage();
                        enemy.acceptDamage(damage);
                        
                        if (enemy.getHealth() <=0 )
                        {
                            result.append(enemy.getName()+" dies. ");
                            result.append(marine.getName()+" survives. ");
                            enemyList.remove(0);
                            break;
                        }
                    }
                    else
                    {                        
                        result.append(marine.getName()+" dies. ");
                        result.append(enemy.getName()+" survives. ");
                        marineList.remove(0);
                        break;
                    }
                    
                }
                		
                return result.toString();
                 
	}
    
}
