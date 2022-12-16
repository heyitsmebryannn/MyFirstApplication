package com.bryan.apartment.users;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserListTable extends JTable{


    String[] titleTable = {"User ID","Full Name","Username","Position"};
    DefaultTableModel defaultTableModel;


    public UserListTable(){

        defaultTableModel = new DefaultTableModel(null,titleTable);
        this.setModel(defaultTableModel);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < this.getColumnModel().getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setResizable(false);
            this.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
            this.getColumnModel().getColumn(i).setPreferredWidth(120);
        }
        this.getTableHeader().setReorderingAllowed(false);
        this.setFont(new Font("Arial",Font.BOLD,12));
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setRowHeight(20);

        ShowUser showUser = new ShowUser();
        showUser.showDataOnTable(this);

    }


}
