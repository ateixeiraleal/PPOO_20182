import java.util.ArrayList;
public class Agencia{
  private String numero;
  private String nome;
  private ArrayList<Conta> contas;
  
  public Agencia(String numero, String nome){
    contas = new ArrayList<>();
    this.numero = numero;
    this.nome = nome;
  }
  
  public void criarConta(){
    contas.add(new Conta(contas.size()+1));
  }
  
  public String getRelatorio(){
    String res = "Lista de Contas Bancarias:";
    
    if(contas.size() > 0){
      for(Conta c: contas){
        res += "\n\nNumero: " + c.getNumero() + "\nSaldo: " + c.getSaldo();
      }
    }else{
      res += "\nNao ha contas cadastradas nesta agencia!";
    }
    
    return res;
  }
  
  public void depositar(int numeroConta, double valor) throws ContaInvalidaException{
    Conta contaDestino = getConta(numeroConta);
    if(contaDestino != null){
      contaDestino.depositar(valor);
    }
    else{
      throw new ContaInvalidaException("Não conseguimos localizar a conta informada!");
    }
  }

  public void sacar(int numeroConta, double valor) throws ContaInvalidaException{
    Conta contaOrigem = getConta(numeroConta);
    if(contaOrigem != null){
        try{
            contaOrigem.sacar(valor);
        }
        catch(SaldoInvalidoException e){
            System.out.println(e.getMessage());
        }
    }
    else{
      throw new ContaInvalidaException("Não conseguimos localizar a conta informada!");
    }
  }
  
  
  public void transferir(int numeroContaOrigem, int numeroContaDestino, double valor)
          throws ContaInvalidaException, SaldoInvalidoException{
    Conta contaOrigem = getConta(numeroContaOrigem);
    Conta contaDestino = getConta(numeroContaDestino);
    
    if(contaOrigem != null){
        if(contaOrigem.getSaldo() >= valor) {
            if(contaDestino != null) {
                contaOrigem.transferir(contaDestino, valor);
            } else {
                throw new ContaInvalidaException("Não conseguimos localizar a conta de destino!");
            }
        }else {
            throw new SaldoInvalidoException("Não há saldo suficiente na conta de origem!");
        }
    } else{
        throw new ContaInvalidaException("Não conseguimos localizar a conta de origem!");
    } 
  }
  

  public Conta getConta(int numeroConta){
    for(Conta c: contas){
      if(c.getNumero() == numeroConta){
	return c;
      }
    }
    return null;
  }

  public int getQdeContas() throws ContaInvalidaException {
      if(contas.size() == 0) {
            throw new ContaInvalidaException("Não existem contas cadastradas!");
        }else 
      return contas.size();
  }
}
