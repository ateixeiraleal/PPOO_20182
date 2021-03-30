package pratica4;

public class TesteEmpresa {
    public static void main(String[] args) {
        Empresa empresa = new Empresa("EmpresaX","123456789/1000");
        Funcionario func1 = new Funcionario_Horista("Luiz","123456789-10",100.0,160.5);
        Funcionario func2 = new Funcionario_Comissionado("Zé","987654321-00",60,50.0);
        Funcionario func3 = new Funcionario_Horista("Carlos","273456789-30",10.26,244);
        
        empresa.admitirFuncionario(func1);
        empresa.admitirFuncionario(func2);
        empresa.admitirFuncionario(func3);
        empresa.gerarFolhaDePagamento();
    }
}