//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2015 uniCenta & previous Openbravo POS works
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

package com.openbravo.pos.ticket;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.sales.JTicketsBagTicket;
import com.openbravo.pos.sales.shared.*;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.DataLogicSales;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author JG uniCenta
 */
public class JTicketsUnfinishedList extends javax.swing.JDialog {
    
    private FindTicketsInfo m_sDialogTicket;
    
    private final DataLogicSales m_dlSales; 
    
    /** Creates new form JTicketsBagSharedList */
    private JTicketsUnfinishedList(java.awt.Frame parent, boolean modal, DataLogicSales dlSales) {
        super(parent, modal);
        m_dlSales = dlSales;        
    }
    /** Creates new form JTicketsBagSharedList */
    private JTicketsUnfinishedList(java.awt.Dialog parent, boolean modal, DataLogicSales dlSales) {
        super(parent, modal);
        m_dlSales = dlSales; 
    }

    /**
     *
     * @param atickets
     * @return
     */
    public FindTicketsInfo showTicketsList(java.util.List<FindTicketsInfo> atickets) {
        
        for (FindTicketsInfo aticket : atickets) {
            m_jtickets.add(new JButtonTicket(aticket));
        }  
     
        m_sDialogTicket = null;

        setVisible(true);
        return m_sDialogTicket;
    }
    
   
    /**
     *
     * @param ticket
     * @return
     */
    public static JTicketsUnfinishedList newJDialog(JTicketsBagTicket ticket, DataLogicSales dlSales) {
        
        Window window = getWindow(ticket);
        JTicketsUnfinishedList mydialog;
        if (window instanceof Frame) { 
            mydialog = new JTicketsUnfinishedList((Frame) window, true, dlSales);
        } else {
            mydialog = new JTicketsUnfinishedList((Dialog) window, true, dlSales);
        } 
        
        mydialog.initComponents();
        
        mydialog.jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(35, 35));
        mydialog.jScrollPane1.getHorizontalScrollBar().setPreferredSize(new Dimension(25, 25));

        
        return mydialog;
    }
    
    private static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }  

    private class JButtonTicket extends JButton {
        
        private final FindTicketsInfo m_Ticket;
        
        public JButtonTicket(FindTicketsInfo ticket){
            
            super();
            
            m_Ticket = ticket;
            setFocusPainted(false);
            setFocusable(false);
            setRequestFocusEnabled(false);
            setMargin(new Insets(8, 14, 8, 14));
            setFont(new java.awt.Font ("Dialog", 0, 14));
            setBackground(new java.awt.Color (220, 220, 220));
            addActionListener(new ActionListenerImpl());
            
            setText(ticket.getTicketId()+","+ticket.getTicketType()+ "," + ticket.getTicketStatus());
            
        }

        private class ActionListenerImpl implements ActionListener {

            public ActionListenerImpl() {
            }

            @Override
            public void actionPerformed(ActionEvent evt) {
                        
                        
                        try{
                            if(m_dlSales.updateTicketStatus(m_Ticket.getTicketId(), 2) > 0){
                                // Selecciono el ticket
                                m_sDialogTicket = m_Ticket;
                                // y oculto la ventana
                                //JTicketsUnfinishedList.this.setVisible(false);
                            }else{
                                m_jtickets.removeAll();
                                java.util.List<FindTicketsInfo> l = m_dlSales.getUnfinishedList();
                                showTicketsList(l);
                            }
                        }catch(BasicException be){
                            return;
                        }
                        JTicketsUnfinishedList.this.setVisible(false);                       
                    }
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        m_jtickets = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        m_jButtonCancel = new javax.swing.JButton();

        setTitle(AppLocal.getIntString("caption.tickets")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(600, 400));
        jPanel2.setLayout(new java.awt.BorderLayout());

        m_jtickets.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        m_jtickets.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jtickets.setLayout(new java.awt.GridLayout(0, 1, 5, 5));
        jPanel2.add(m_jtickets, java.awt.BorderLayout.NORTH);

        jScrollPane1.setViewportView(jPanel2);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        jPanel3.add(jPanel4);

        m_jButtonCancel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        m_jButtonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/cancel.png"))); // NOI18N
        m_jButtonCancel.setText(AppLocal.getIntString("Button.Close")); // NOI18N
        m_jButtonCancel.setFocusPainted(false);
        m_jButtonCancel.setFocusable(false);
        m_jButtonCancel.setMargin(new java.awt.Insets(8, 16, 8, 16));
        m_jButtonCancel.setRequestFocusEnabled(false);
        m_jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jButtonCancelActionPerformed(evt);
            }
        });
        jPanel3.add(m_jButtonCancel);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(411, 335));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void m_jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jButtonCancelActionPerformed

        dispose();
        
    }//GEN-LAST:event_m_jButtonCancelActionPerformed
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton m_jButtonCancel;
    private javax.swing.JPanel m_jtickets;
    // End of variables declaration//GEN-END:variables
    
}
