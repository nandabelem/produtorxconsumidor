/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtorxconsumidor;

import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;
import gui.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author fernanda
 */
public class Produtor extends Thread {
    
    private final int idProdutor;
    private final Buffer buffer;
    private Sincroniza sincroniza;
    private Lock lock;
    private Log log;
    private final Thread threadDaInterface;
    public static ArrayList<String> array = new ArrayList();
    

    public Produtor(int idProdutor, Buffer buffer, Sincroniza sincroniza, Lock lock, Log log, Thread threadDaInterface) {
        this.idProdutor = idProdutor;
        this.buffer = buffer;
        this.sincroniza = sincroniza;
        this.lock = lock;
        this.log = log;
        this.threadDaInterface = threadDaInterface;
    }        

    public int getIdProdutor() {
        return idProdutor;
    }
	
    /**
     *Fiz isso so pro NetBeans parar de encher o saco.
     */
    @Override
    public void run(){  
        
        while(true){
            lock.lock();
            try{
            array.add("Iniciou Produtor "+idProdutor);
            try {
                array.add("O produtor "+idProdutor+" foi dormir ZzzZz");
                sleep(valorEspera());
                array.add("O produtor "+idProdutor+" acordou e foi tentar a permissão!");
            } catch (InterruptedException ex) {
                Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
            }
            String key = sincroniza.getPermissao();
            while(!key.equals("VoceTemPermissao")){
                array.add("O produtor "+idProdutor+" não teve permissão");
                try {
                    array.add("O produtor "+idProdutor+" foi dormir ZzzZz");
                    sleep(valorEspera());
                    array.add("O produtor "+idProdutor+" acordou e foi tentar a permissão!");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
                }
            key = sincroniza.getPermissao();
            }
            array.add("O prudutor "+idProdutor+" pegou a permissão.");
            int resultado = buffer.populaBuffer(idProdutor);
            if( resultado != 20){
                array.add("O Produtor N"+idProdutor+" escreveu na posição "+resultado+"do buffer.");
                array.add("O buffer está com "+ buffer.imprimeBuffer() +" posições ocupadas.");                    
            }else{
                array.add("O Buffer está cheio, o Produtor "+idProdutor+" não pode escrever.");
            }
            sincroniza.setPermissao(key);
            array.add("O produtor "+idProdutor+" terminou e devolveu a permissão.");            
            }finally{                
                threadDaInterface.notify();
                lock.unlock();            
            }          
                             
        }
        
    }

    public static ArrayList getArray(){
        return array;
    }
            
    public int valorEspera(){
        Random valor = new Random();
        return valor.nextInt(999) + 1;      
    }

}
    

