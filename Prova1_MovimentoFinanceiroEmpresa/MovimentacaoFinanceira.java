class MovimentacaoFinanceira {
private static double[] taxaFixa = {100,200,300,400,500,600,700,800,900,1000,1100,1200};
  private int dia;
  private int mes;
  private int ano;
  private String horario;
  private double valor;
  
  public MovimentacaoFinanceira(int dia, int mes, int ano, String horario, double valor){
    this.dia = dia;
    this.mes = mes;
    this.ano = ano;
    this.horario = horario;
    this.valor = valor;
  }
  
  public int getDia(){
    return dia;
  }
  
  public int getMes(){
    return mes;
  }
  
  public int getAno(){
    return ano;
  }
  
  public String getHorario(){
    return horario;
  }
  
  public double getValor(){
    return valor;
  }
  
  public double retornarTaxa() {
      switch (getMes()) {
          case 1:
              return taxaFixa[0];
          case 2:
              return taxaFixa[1];
          case 3:
              return taxaFixa[2];
          case 4:
              return taxaFixa[3];
          case 5:
              return taxaFixa[4];
          case 6:
              return taxaFixa[5];
          case 7:
              return taxaFixa[6];
          case 8:
              return taxaFixa[7];
          case 9:
              return taxaFixa[8];
          case 10:
              return taxaFixa[9];
          case 11:
              return taxaFixa[10];
          case 12:
              return taxaFixa[11];    
          default:
              throw new AssertionError();
      }
  }
}
