package com.credibanco.assessment.card.util;

import com.credibanco.assessment.card.exceptions.ProcessException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MaskedPANUtil {

  public static String getMaskedPAN(final String pan, final char maskChar) throws ProcessException {
    final Integer length = pan.length();
    switch (length) {
      case 16:
        return maskString(pan, 6, 12, maskChar);
      case 17:
        return maskString(pan, 6, 13, maskChar);
      case 18:
        return maskString(pan, 6, 14, maskChar);
      case 19:
        return maskString(pan, 6, 15, maskChar);
      default:
        break;
    }
    return pan;
  }

  private static String maskString(final String strText, int start, int end, final char maskChar)
      throws ProcessException {

    if (strText == null || strText.equals("")) {
      return "";
    }

    if (start < 0) {
      start = 0;
    }

    if (end > strText.length()) {
      end = strText.length();
    }

    if (start > end) {
      throw new ProcessException("El index final no puede ser mas grande que indice inicial");
    }

    final int maskLength = end - start;

    if (maskLength == 0) {
      return strText;
    }

    final StringBuilder sbMaskString = new StringBuilder(maskLength);

    for (int i = 0; i < maskLength; i++) {
      sbMaskString.append(maskChar);
    }

    return strText.substring(0, start)
        + sbMaskString.toString()
        + strText.substring(start + maskLength);
  }
}
