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

package com.openbravo.pos.forms;

import com.openbravo.basic.BasicException;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author adrianromero
 */
public class JPanelNull extends JPanel implements JPanelView {

    /** Creates new form JPanelNull
     * @param oApp
     * @param o */
    public JPanelNull(AppView oApp, Object o) {
       
        initComponents ();
        if (o instanceof Exception) {
        }
        jtxtException.setText(o.toString());
    }

    /**
     *
     * @return
     */
    @Override
    public JComponent getComponent() {
        return this;
    }

    /**
     *
     * @return
     */
    @Override
    public String getTitle() {
        return null;
    }

    /**
     *
     * @throws BasicException
     */
    @Override
    public void activate() throws BasicException {
    }

    /**
     *
     * @return
     */
    @Override
    public boolean deactivate() {
        return true;
    }    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_jLabelError = new javax.swing.JLabel();
        jscrException = new javax.swing.JScrollPane();
        jtxtException = new javax.swing.JTextArea();

        setLayout(null);

        m_jLabelError.setFont(new java.awt.Font("MS Song", 0, 12)); // NOI18N
        m_jLabelError.setText(AppLocal.getIntString("Label.LoadError")); // NOI18N
        add(m_jLabelError);
        m_jLabelError.setBounds(30, 30, 490, 20);

        jtxtException.setEditable(false);
        jtxtException.setFont(new java.awt.Font("MS Song", 0, 12)); // NOI18N
        jtxtException.setLineWrap(true);
        jtxtException.setWrapStyleWord(true);
        jscrException.setViewportView(jtxtException);

        add(jscrException);
        jscrException.setBounds(30, 70, 550, 180);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jscrException;
    private javax.swing.JTextArea jtxtException;
    private javax.swing.JLabel m_jLabelError;
    // End of variables declaration//GEN-END:variables

}
