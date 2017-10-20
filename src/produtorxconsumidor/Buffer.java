/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtorxconsumidor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author aluno-linux
 */
public class Buffer {
    
        private final int tamanhoBuffer;
	private int[] vetorBuffer;
        Lock lock = new ReentrantLock();
        
	
	
	public Buffer(int tamanhoBuffer) {
		this.tamanhoBuffer = tamanhoBuffer;
                vetorBuffer = new int[tamanhoBuffer];
	}
        
        public void iniciarBuffer(){
            for(int i = 0; i < tamanhoBuffer; i++){
                vetorBuffer[i] = 0;
            }
        }
 
	
	public int  populaBuffer(int referenciaID) {
            lock.lock();
            for(int i = 0; i < tamanhoBuffer; i++) {
			if(vetorBuffer[i] == 0) {
				vetorBuffer[i] = referenciaID;
                                lock.unlock();
				return i;
			}
		}
                lock.unlock();
                return 20;
	}
        
        public int consomeBuffer(int referenciaID) {
            lock.lock();
		for(int i = 0; i < tamanhoBuffer; i++) {
			if(vetorBuffer[i] == referenciaID){
				vetorBuffer[i] = 0;
                                lock.unlock();
				return i;
			}
		}
                lock.unlock();
		return 20;
	}
        
        public int imprimeBuffer(){
            int posicoes = 0;
            for(int i = 0; i < tamanhoBuffer; i++){
               if(vetorBuffer[i] != 0){
                   posicoes++;                   
               }
            }
            return posicoes;
        }
    
}
