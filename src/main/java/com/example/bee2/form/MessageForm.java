package com.example.bee2.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageForm {
  private String reciever;
  private String title;
  private String text;
}
