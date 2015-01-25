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

import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author mephisto9000
 */


public class SpaceMarine {
    
    private static int MAX_HEALTH = 1000;
        
    private String name;

   
    private String chapter;
    private int kills;
    private SMRank rank;
    private SMStatus status;
    private SMLoyalty loyalty;
    
    private int health;
    private int damage;
    
    
    
    
    
    public SpaceMarine(String name, String chapter, int kills, SMRank rank, SMLoyalty loyalty, int damage)
    {
        super();
        this.name = name;
        this.chapter = chapter;
        this.kills = kills;
        this.rank = rank;
        this.loyalty = loyalty;
        
        this.status = SMStatus.Healthy;
        this.health = MAX_HEALTH;
        this.damage = damage;
        
        
        
    }
    
     public String getName() {
        return name;
    }

    public String getChapter() {
        return chapter;
    }

    public int getKills() {
        return kills;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }
    
    
    public SMStatus getStatus()
    {
        return this.status;
    }
    
    public SMLoyalty getLoyalty()
    {
        return loyalty;
    }
    
    public SMRank getRank()
    {
        return rank;
    }               
    
    public void addKill()
    {
        kills++;
    }
    
    public void addKills(int deltaKills)
    {
        kills += deltaKills;
    }
    
    public void acceptDamage(int deltaDamage)
    {
        health -= deltaDamage;
        
        if (health > 0)
            this.status = SMStatus.Injured;
        else
            this.status = SMStatus.Deceased;
    }
    
    public int doDamage()
    {
        return damage;
    }
    
    
    
    
    
    
    
}
