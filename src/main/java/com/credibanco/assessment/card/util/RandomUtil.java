package com.credibanco.assessment.card.util;

import java.util.Random;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtil {

  public static Integer generateValidationNumber() {
    final Random rand = new Random();
    return rand.nextInt(101);
  }
}
