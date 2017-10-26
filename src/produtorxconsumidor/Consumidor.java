/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtorxconsumidor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import gui.*;

/**
 *
 * @author aluno-linux
 */
public class Consumidor extends Thread{
    
    private final int idConsumidor;
    private final Buffer buffer;
    private Sincroniza sincroniza;
    private Lock lock;
    private Log log;
    private Thread threadDaInterface;

    public Consumidor(int idConsumidor, Buffer buffer, Sincroniza sincroniza, Lock lock, Log log, Thread threadDaInterface){
        this.idConsumidor = idConsumidor;
        this.buffer = buffer;
        this.sincroniza = sincroniza;
        this.lock = lock;
        this.log = log;
        this.threadDaInterface = threadDaInterface;
    }

    public int getIdConsumidor() {
	return idConsumidor;
    }
		
    /**
     *Fiz isso so pro NetBeans parar de encher o saco.
     */
    @Override
    public void run() {
        while(true){
            lock.lock();
            try{
            System.out.println("Iniciou Consumidor "+idConsumidor);
            
            System.out.flush();            
            try {
                System.out.println("O consumidor "+idConsumidor+" foi dormir ZzzZz");
                System.out.flush();
                sleep(200); 
                System.out.println("O consumidor "+idConsumidor+" acordou e foi tentar a permissão");
                System.out.flush();
            } catch (InterruptedException ex) {
                Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
            }            
            String key = sincroniza.getPermissao();
            while(!key.equals("VoceTemPermissao")){
                System.out.println("O consumidor "+idConsumidor+" teve permissão negada!");
                System.out.flush();                
                try {
                    System.out.println("O consumidor "+idConsumidor+" foi dormir ZzzZz");
                    System.out.flush();
                    sleep(1000);
                    System.out.println("O consumidor "+idConsumidor+" acordou e foi tentar a permissão");
                    System.out.flush();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
                }
               key = sincroniza.getPermissao();                        
            }
                System.out.println("O consumidor "+idConsumidor+" pegou a permissão.");
                System.out.flush();
                int resultado = buffer.consomeBuffer(idConsumidor);
                    if( resultado != 20){
                        System.out.println("O Consumidor N"+idConsumidor+" consumiu na posição "+resultado+"do buffer.");
                        System.out.flush();
                        System.out.println("O buffer está com "+ buffer.imprimeBuffer() +" posições ocupadas.");
                        System.out.flush();
                    }else{
                        System.out.println("Não havia nada disponível no buffer para o Consumidor "+idConsumidor);
                        System.out.flush();
                    }
                    sincroniza.setPermissao(key);         
                    System.out.println("O consumidor "+idConsumidor+" terminou e devolveu a permissão.");   
                    System.out.flush();
            }finally{
                    lock.unlock();                    
            }
      }
            
    }
    
}
