/*
 * Copyright 2002-2012 the original author or authors.
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


public class CalcService {
            
        private static int sum = 0;

	public String saySum(String name) {
            
                //System.out.println("message received: "+name);
                try {
                    int i = Integer.parseInt(name);
                    sum += i;
                }
                catch (Exception e)
                {
                    System.out.println("!!error parsing number!!");
                }
                finally
                {
                    System.out.println("sum = "+sum);
                }
                
                
		return "you typed '"+name+"', current sum = " + sum;   
                
                 
	}
        
        public static void clearSum()
        {
            sum = 0;
        }

}
