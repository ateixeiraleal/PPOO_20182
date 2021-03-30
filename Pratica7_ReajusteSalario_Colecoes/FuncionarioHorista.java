package PPOO_20182.Pratica7_ReajusteSalario_Colecoes;

public class FuncionarioHorista extends Funcionario{
    private double salario;
    private double horasTrabalahadas;

    public FuncionarioHorista(String nome, String cpf, double salario, double horasTrabalahadas) {
        super(nome, cpf);
        this.salario = salario;
        this.horasTrabalahadas = horasTrabalahadas;
    }

    public double getSalario() {
        return salario;
    }

    public double getHorasTrabalahadas() {
        return horasTrabalahadas;
    }
    
    @Override
    public double calcularSalario() {
        return salario * horasTrabalahadas;
    } 
}
