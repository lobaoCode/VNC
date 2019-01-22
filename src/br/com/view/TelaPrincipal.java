/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.view;

import javax.swing.JFrame;
import br.com.model.Usuario;
import br.com.controller.PCcontroller;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author gustavo.santos
 */
public class TelaPrincipal extends JFrame {

    
    private MigLayout migLayout;
    private MigLayout migLayout02;
    private MigLayout migLayout03;
    
    private final PCcontroller controler;
    private Usuario usuario;
    private List<String> listaPC;

    private JPanel painel01;
    private JPanel painel02;

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JLabel lblUsuario;
    private JLabel lblSenha;
    private JButton btnSetar;
    private JButton btnChange;

    private JScrollPane scrPC;
    
    private JButton[] btnPC;

    public TelaPrincipal() {
        controler = new PCcontroller();
        initComponentes();
    }

    public final void initComponentes() {
        
        migLayout =  new MigLayout();
        migLayout02 =  new MigLayout("", "[]");
        migLayout03 =  new MigLayout();
        
        this.setSize(520, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(migLayout);

        painel02 = new JPanel();
        painel02.setLayout(migLayout02);
        
        txtSenha = new JPasswordField();
        txtUsuario = new JTextField();
        lblSenha = new JLabel("SENHA");
        lblUsuario = new JLabel("USUARIO");
        btnSetar = new JButton("SET");
        btnSetar.addActionListener((ActionEvent e) -> {
            actSetar();
        });
        btnChange = new JButton("TROCAR");
        btnChange.addActionListener((ActionEvent e) -> {
            actTrocar();
        });
        
        

        painel02.add(lblUsuario, "span 2, growx,wrap");
        painel02.add(txtUsuario, "span 2, growx, wrap 50px");
        painel02.add(lblSenha, "wrap, grow");
        painel02.add(txtSenha, "span 2, growx, wrap");
        painel02.add(btnSetar);
        painel02.add(btnChange);
        

        painel01 = new JPanel();
        painel01.setLayout(migLayout03);

        scrPC = new JScrollPane(painel01);
        scrPC.getVerticalScrollBar().setUnitIncrement(10);
        
        
     
        this.add(scrPC, "width 80%, height 100%");
        this.add(painel02,"width 20%, height 90%");
        this.setResizable(false);
        this.setVisible(true);
    }

    private void actSetar() {
        usuario = new Usuario(txtUsuario.getText(), txtSenha.getText());
        this.setUsuario(usuario);
        txtSenha.setEnabled(false);
        txtUsuario.setEnabled(false);
        setListaPC(new ArrayList<>());    
        addPC(controler.listaPC(getListaPC()));
    }
    
    private void addPC(List<String> lista){
        
        btnPC = new JButton[lista.size()];
        
        for (int i = 0; i < lista.size(); i++) {
            btnPC[i] = new JButton(lista.get(i));
            btnPC[i].addActionListener((ActionEvent e) -> {
                actBtnPc(e.getActionCommand());
            });
            if(i%2 == 0){
                painel01.add(btnPC[i],"span 1, growx");
            }else{
                painel01.add(btnPC[i],"wrap,  span 1, growx");
            }
            painel01.revalidate();
        }
    }

    private void actBtnPc(String nomePC){
        controler.comando("c:\\Program Files\\uvnc bvba\\UltraVNC\\vncviewer.exe "+
                "-user "+usuario.getNome()+" -password "+usuario.getSenha()+" -connect host "+nomePC+" -autoscaling");
        
    }
    private void actFechar(){
        System.exit(0);
    }
    
    
    private void actTrocar() {       
        painel01.removeAll();
        txtSenha.setEnabled(true);
        txtUsuario.setEnabled(true);
        txtUsuario.setText("");
        txtSenha.setText("");
        painel01.revalidate();
        painel02.revalidate();
    }
    
    public PCcontroller getControler() {
        return controler;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
 
    public List<String> getListaPC() {
        return listaPC;
    }

    public void setListaPC(List<String> listaPC) {
        this.listaPC = listaPC;
    }
}
