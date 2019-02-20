package com.example.bee2.utility;

import org.springframework.stereotype.Component;

@Component
public class RequestUtility {
  public boolean isMobile(String userAgent) {
    boolean mobile = false;
    String[] mobileAgents = { "iPhone", "iPad", "iPod", "iPod touch", "Android" };
    
    for (String agent : mobileAgents) mobile |= userAgent.contains(agent);
    
    return mobile;
  }
}
