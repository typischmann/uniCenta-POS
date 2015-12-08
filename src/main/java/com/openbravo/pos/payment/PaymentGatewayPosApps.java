//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright (C) 2011-2015 
//    http://www.unicenta.com
//
//    This file is part of uniCenta oPOS
//
//    uniCenta oPOS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   uniCenta oPOS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with uniCenta oPOS.  If not, see <http://www.gnu.org/licenses/>.
package com.openbravo.pos.payment;

import com.handpoint.headstart.api.FinancialTransactionResult;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.pos_apps.PosApps;
import uk.co.pos_apps.payment.Handpoint;

/**
 *
 * @author Hugh Stevenson uniCenta
 */
public class PaymentGatewayPosApps implements PaymentGateway {

  /**
   * Creates a new instance of PaymentGatewayExt
   */
  public PaymentGatewayPosApps() {
  }

  /**
   *
   * @param payinfo
   */
  @Override
  public void execute(PaymentInfoMagcard payinfo) {

    //Initilise as null so we have a new object each time
    PosApps posApps = null;
    Handpoint.setFinancialTransactionResult(null);
    Handpoint.setPaymentComplete(Boolean.FALSE);
    Handpoint.setDeviceState(null);
    Handpoint.setAMOUNT(null);

    //Timeout 
    int timer = 0;
    int timeout = 120;
    payinfo.setChipAndPin(Boolean.TRUE);

    posApps = new PosApps();
    posApps.processPayment(payinfo.getTotal());

    payinfo.paymentError("Integrated Payment Machine  ", "Waiting for payment to complete ...");

    while (!Handpoint.PAYMENT_COMPLETE) {
      if (timer < timeout) {
        System.out.println("waiting to complete ");
        payinfo.paymentError("PosApps Pay  ", Handpoint.getDeviceState());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ex) {
          Logger.getLogger(PaymentGatewayPosApps.class.getName()).log(Level.SEVERE, null, ex);
        }
        timer = timer + 1;
      } else {
        System.out.println("transaction timed out ... ");
        Handpoint.setDeviceState("transaction timed out ... please try again");
        Handpoint.setPaymentComplete(Boolean.TRUE);
      }
    }

    FinancialTransactionResult result = Handpoint.getFinancialTransactionResult();
    if (result != null) {
      if (result.transactionStatus == 1) {
        System.out.println(" Total  : " + payinfo.m_dTotal);
        payinfo.setCardName(result.cardSchemeName);
        payinfo.setVerification(result.cvm);
        payinfo.paymentOK(result.authorisationCode, payinfo.getTransactionID(), " ");
        Handpoint.setDeviceState(result.financialStatus);

      } else {
        //Reset Values for next transaction
        Handpoint.setDeviceState("Error with payment - Reason: " + result.financialStatus);
        payinfo.paymentError("Integrated Payment Machine  ", "Error with payment");
        Handpoint.setFinancialTransactionResult(null);
        Handpoint.setPaymentComplete(Boolean.FALSE);
        Handpoint.setAMOUNT(null);
      }
    } else {
      //Reset Values for next transaction
      Handpoint.setDeviceState("Error with payment - Reason: FinancialTransactionResult is null " );
      payinfo.paymentError("Integrated Payment Machine  ", "FinancialTransactionResult is null");
      Handpoint.setFinancialTransactionResult(null);
      Handpoint.setPaymentComplete(Boolean.FALSE);
      Handpoint.setAMOUNT(null);

    }

  }
}
