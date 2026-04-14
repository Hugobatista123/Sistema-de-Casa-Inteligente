
interface Autenticavel {
  void validarAcesso(String senha);
}

interface DispositivoLigavel {
  void ligar();
  void desligar();
}


interface SensorTemperatura {
  double lerTemperatura();
}


class FechaduraEletronica implements Autenticavel, DispositivoLigavel {

  private final String senhaCorreta;
  private boolean acessoPermitido = false;
  private boolean ligada = false;

  public FechaduraEletronica(String senhaCorreta) {
    this.senhaCorreta = senhaCorreta;
  }

  @Override
  public void validarAcesso(String senha) {
    if (senha.equals(senhaCorreta)) {
      acessoPermitido = true;
      System.out.println("Acesso permitido!");
    } else {
      acessoPermitido = false;
      System.out.println("Acesso negado!");
    }
  }

  @Override
  public void ligar() {
    if (acessoPermitido) {
      ligada = true;
      System.out.println("Fechadura destrancada!");
    } else {
      System.out.println("Não é possível destrancar. Acesso não validado.");
    }
  }

  @Override
  public void desligar() {
    ligada = false;
    acessoPermitido = false;
    System.out.println("Fechadura trancada!");
  }
}


// Classe TermostatoSmart
class TermostatoSmart implements DispositivoLigavel, SensorTemperatura {

  private boolean ligado = false;

  @Override
  public void ligar() {
    ligado = true;
    System.out.println("Termostato ligado!");
  }

  @Override
  public void desligar() {
    ligado = false;
    System.out.println("Termostato desligado!");
  }

  @Override
  public double lerTemperatura() {
    if (!ligado) {
      System.out.println("Termostato desligado. Não é possível ler a temperatura.");
      return 0.0;
    }
    // Simulação de leitura de temperatura
    double temperatura = 20 + Math.random() * 10;
    return temperatura;
  }
}


// Classe principal para teste
public class Main {
  public static void Main(String[] args) {

    // Testando FechaduraEletronica
    FechaduraEletronica fechadura = new FechaduraEletronica("1234");

    fechadura.validarAcesso("1111"); // errado
    fechadura.ligar();

    fechadura.validarAcesso("1234"); // correto
    fechadura.ligar();
    fechadura.desligar();

    System.out.println("------------------------");

    // Testando TermostatoSmart
    TermostatoSmart termostato = new TermostatoSmart();

    termostato.ligar();
    System.out.println("Temperatura atual: " + termostato.lerTemperatura());
    termostato.desligar();
  }
}