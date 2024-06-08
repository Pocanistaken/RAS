
package com.ras.entity;

import com.ras.enums.PaymentType;
import java.time.Instant;


public record Bill (int billID, int billTableID, double billTotal, PaymentType billPaymentType, Instant billDate) {}
