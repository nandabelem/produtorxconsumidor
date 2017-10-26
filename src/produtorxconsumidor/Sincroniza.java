/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtorxconsumidor;


/**
 *
 * @author fernanda
 */
public class Sincroniza {
    
    private boolean permissao;
    private final String key;

    public Sincroniza() {
        this.key = "VoceTemPermissao";
        this.permissao = true;
    }
    
    public String getPermissao(){
        if(permissao == true){
            this.permissao = false;            
        
        }
         return this.key;        
       }
    
    public void setPermissao(String permissao){
        if(this.key.equals(permissao)){
            this.permissao = true;
        }        
    }

    
        
    
}
