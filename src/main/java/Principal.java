import corejava.Console;
import excecao.*;
import modelo.*;
import servico.*;
import singleton.SingletonPerfis;
import util.Util;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

public class Principal {
    @SuppressWarnings("resource")
    static
    ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

    public static int chamaPresidiario() throws PresidiarioNaoEncontradoException {
        PresidiarioAppService presidiarioAppService = (PresidiarioAppService) fabrica.getBean("presidiarioAppService");

        int opcaoPresi = 0;
        String nome;
        String dataEntrada;
        String dataSoltura;
        Presidiario novoPresidiario;
        do {
            System.out.println('\n' + "As seguintes operações podem ser feitas:");
            System.out.println('\n' + "1. Cadastrar um presidiario");
            System.out.println("2. Alterar um presidiario");
            System.out.println("3. Remover um presidiario");
            System.out.println("4. Listar todos os presidiarios");
            System.out.println("5. Voltar ao menu anterior");
            System.out.println("0. Sair");

            opcaoPresi = Console.readInt('\n' +
                    "Digite um número entre 0 e 5:");

            switch (opcaoPresi) {
                case 1: {
                    nome = Console.readLine('\n' +
                            "Informe o nome do presidiario: ");
                    dataEntrada = Console.readLine(
                            "Informe a data de entrada: ");
                    dataSoltura = Console.readLine(
                            "Informe a data de saída: ");

                    novoPresidiario = new Presidiario(nome, Util.strToDate(dataEntrada), Util.strToDate(dataSoltura));

                    long numero = presidiarioAppService.inclui(novoPresidiario);

                    System.out.println('\n' + "Presidiario número " +
                            numero + " incluído com sucesso!");

                    break;
                }

                case 2: {
                    int resposta = Console.readInt('\n' +
                            "Digite o número do presidiário que você deseja alterar: ");

                    try {
                        novoPresidiario = presidiarioAppService.recuperaPresidiario(resposta);
                    } catch (PresidiarioNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' +
                            "Número = " + novoPresidiario.getId() +
                            "    Nome = " + novoPresidiario.getNome() +
                            "    Data de Entrada = " + novoPresidiario.getDataPrisao() +
                            "    Data de Saída = " + novoPresidiario.getDataSoltura() +
                            "    Versão = " + novoPresidiario.getVersao());

                    System.out.println('\n' + "O que você deseja alterar?");
                    System.out.println('\n' + "1. Nome");
                    System.out.println("2. Data de Entrada ");
                    System.out.println("3. Data de Saída");


                    int opcaoAlteracao = Console.readInt('\n' +
                            "Digite um número de 1 a 3:");

                    switch (opcaoAlteracao) {
                        case 1:
                            String novoNome = Console.
                                    readLine("Digite o novo nome: ");

                            novoPresidiario.setNome(novoNome);

                            try {
                                presidiarioAppService.altera(novoPresidiario);

                                System.out.println('\n' +
                                        "Alteração de nome efetuada com sucesso!");
                            } catch (PresidiarioNaoEncontradoException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;

                        case 2:
                            String novaDataEntrada = Console.
                                    readLine("Digite a data de entrada: ");

                            novoPresidiario.setDataPrisao(Util.strToDate(novaDataEntrada));

                            try {
                                presidiarioAppService.altera(novoPresidiario);

                                System.out.println('\n' +
                                        "Alteração de data de entrada efetuada " +
                                        "com sucesso!");
                            } catch (PresidiarioNaoEncontradoException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;

                        case 3:
                            String novaDataSoltura = Console.
                                    readLine("Digite s data de soltura: ");

                            novoPresidiario.setDataSoltura(Util.strToDate(novaDataSoltura));

                            try {
                                presidiarioAppService.altera(novoPresidiario);

                                System.out.println('\n' +
                                        "Alteração de data de soltura efetuada " +
                                        "com sucesso!");
                            } catch (PresidiarioNaoEncontradoException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;


                        default:
                            System.out.println('\n' + "Opção inválida!");
                    }

                    break;
                }

                case 3: {
                    int resposta = Console.readInt('\n' +
                            "Digite o número do presidiario que você deseja remover: ");

                    try {
                        novoPresidiario = presidiarioAppService.recuperaPresidiario(resposta);
                    } catch (PresidiarioNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' +
                            "Número = " + novoPresidiario.getId() +
                            "    Nome = " + novoPresidiario.getNome() +
                            "    Data Entrada = " + novoPresidiario.getDataPrisao() +
                            "    Data Saída = " + novoPresidiario.getDataSoltura() +
                            "    Versão = " + novoPresidiario.getVersao());

                    String resp = Console.readLine('\n' +
                            "Confirma a remoção do presidiario?");

                    if (resp.equals("s")) {
                        try {
                            presidiarioAppService.exclui(novoPresidiario);
                            System.out.println('\n' +
                                    "Presidiario removido com sucesso!");
                        } catch (PresidiarioNaoEncontradoException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                    } else {
                        System.out.println('\n' +
                                "Presidiario não removido.");
                    }

                    break;
                }

                case 4: {
                    List<Presidiario> presidiarios = presidiarioAppService.recuperaPresidiarios();

                    for (Presidiario presidiario : presidiarios) {
                        System.out.println('\n' +
                                "Número = " + presidiario.getId() +
                                "    Nome = " + presidiario.getNome() +
                                "    Data Entrada = " + presidiario.getDataPrisao() +
                                "    Data Saída = " + presidiario.getDataSoltura() +
                                "    Versão = " + presidiario.getVersao());
                    }

                    break;
                }

                case 0: {
                    return 0;
                }

                case 5:
                    return 5;

                default:
                    System.out.println('\n' + "Opção inválida!");
            }
        } while (true);
    }

    public static int chamaAlocacao() throws AlocacaoNaoEncontradaException {
        AlocacaoAppService alocacaoAppService = (AlocacaoAppService) fabrica.getBean("alocacaoAppService");

        int opcaoAloc = 0;
        long idPresidiario;
        long idCela;
        String dataEntrada;
        String dataSaida;
        Alocacao novaAlocacao;
        Cela cela;
        CelaAppService celaAppService = (CelaAppService) fabrica.getBean("celaAppService");
        Presidiario presidiario;
        PresidiarioAppService presidiarioAppService = (PresidiarioAppService) fabrica.getBean("presidiarioAppService");
        do {
            System.out.println('\n' + "As seguintes operações podem ser feitas:");
            System.out.println('\n' + "1. Cadastrar uma alocaçao");
            System.out.println("2. Alterar uma alocaçao");
            System.out.println("3. Remover uma alocaçao");
            System.out.println("4. Listar todos as alocaçoes");
            System.out.println("5. Voltar ao menu anterior");
            System.out.println("0. Sair");

            opcaoAloc = Console.readInt('\n' +
                    "Digite um número entre 0 e 5:");

            switch (opcaoAloc) {
                case 1: {
                    dataEntrada = Console.readLine('\n' +
                            "Informe a data de entrada: ");
                    dataSaida = Console.readLine(
                            "Informe a data de saída: ");

                    idPresidiario = parseLong(Console.readLine(
                            "Informe o id do presidiario: "));


                    try {
                        presidiario = presidiarioAppService.recuperaPresidiario(idPresidiario);
                    } catch (PresidiarioNaoEncontradoException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    idCela = parseLong(Console.readLine(
                            "Informe o id da cela: "));

                    try {
                        cela = celaAppService.recuperaCela(idCela);
                    } catch (CelaNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    novaAlocacao = new Alocacao(Util.strToDate(dataEntrada), Util.strToDate(dataSaida), cela, presidiario);

                    long numero = alocacaoAppService.inclui(novaAlocacao);

                    System.out.println('\n' + "Alocaçao número " +
                            numero + " incluído com sucesso!");

                    break;
                }

                case 2: {
                    int resposta = Console.readInt('\n' +
                            "Digite o número da alocaçao que você deseja alterar: ");

                    try {
                        novaAlocacao = alocacaoAppService.recuperaAlocacao(resposta);
                    } catch (AlocacaoNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' +
                            "Número = " + novaAlocacao.getId() +
                            "    Data de Entrada = " + novaAlocacao.getDataEntrada() +
                            "    Data de Saída = " + novaAlocacao.getDataSaida() +
                            "    Id Presidiário = " + novaAlocacao.getPresidiario().getId() +
                            "    Id Cela = " + novaAlocacao.getCela().getId() +
                            "    Versão = " + novaAlocacao.getVersao());

                    System.out.println('\n' + "O que você deseja alterar?");
                    System.out.println("1. Data de Entrada ");
                    System.out.println("2. Data de Saída");
                    System.out.println("3. Id Presidiário");
                    System.out.println("4. Id Cela");


                    int opcaoAlteracao = Console.readInt('\n' +
                            "Digite um número de 1 a 4:");

                    switch (opcaoAlteracao) {
                        case 1:
                            String novaDataEntrada = Console.
                                    readLine("Digite a data de entrada: ");

                            novaAlocacao.setDataEntrada(Util.strToDate(novaDataEntrada));

                            try {
                                alocacaoAppService.altera(novaAlocacao);

                                System.out.println('\n' +
                                        "Alteração de data de entrada efetuada " +
                                        "com sucesso!");
                            } catch (AlocacaoNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;

                        case 2:
                            String novaDataSaida = Console.
                                    readLine("Digite s data de saida: ");

                            novaAlocacao.setDataSaida(Util.strToDate(novaDataSaida));

                            try {
                                alocacaoAppService.altera(novaAlocacao);

                                System.out.println('\n' +
                                        "Alteração de data de soltura efetuada " +
                                        "com sucesso!");
                            } catch (AlocacaoNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;

                        case 3:
                            long idPres = parseLong(Console.
                                    readLine("Digite o id do presidiário: "));

                            try {
                                presidiario = presidiarioAppService.recuperaPresidiario(idPres);
                                novaAlocacao.setPresidiario(presidiario);
                            } catch (PresidiarioNaoEncontradoException e) {
                                System.out.println('\n' + e.getMessage());
                            }

                            try {
                                alocacaoAppService.altera(novaAlocacao);

                                System.out.println('\n' +
                                        "Alteração do id presidiário efetuada " +
                                        "com sucesso!");
                            } catch (AlocacaoNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;
                        case 4:
                            long idCel = parseLong(Console.
                                    readLine("Digite o id da cela: "));

                            try {
                                cela = celaAppService.recuperaCela(idCel);
                                novaAlocacao.setCela(cela);
                            } catch (CelaNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            }

                            try {
                                alocacaoAppService.altera(novaAlocacao);

                                System.out.println('\n' +
                                        "Alteração do id cela efetuada " +
                                        "com sucesso!");
                            } catch (AlocacaoNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;


                        default:
                            System.out.println('\n' + "Opção inválida!");
                    }

                    break;
                }

                case 3: {
                    int resposta = Console.readInt('\n' +
                            "Digite o número da alocaçao que você deseja remover: ");

                    try {
                        novaAlocacao = alocacaoAppService.recuperaAlocacao(resposta);
                    } catch (AlocacaoNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' +
                            "Número = " + novaAlocacao.getId() +
                            "    Data Entrada = " + novaAlocacao.getDataEntrada() +
                            "    Data Saída = " + novaAlocacao.getDataSaida() +
                            "    Id presidiário = " + novaAlocacao.getPresidiario().getId() +
                            "    Id cela = " + novaAlocacao.getCela().getId() +
                            "    Versão = " + novaAlocacao.getVersao());

                    String resp = Console.readLine('\n' +
                            "Confirma a remoção da alocaçao? (S/N)");

                    if (resp.equals("s")) {
                        try {
                            alocacaoAppService.exclui(novaAlocacao);
                            System.out.println('\n' +
                                    "Alocaçao removida com sucesso!");
                        } catch (AlocacaoNaoEncontradaException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                    } else {
                        System.out.println('\n' +
                                "Alocaçao não removida.");
                    }

                    break;
                }

                case 4: {
                    List<Alocacao> alocacoes = alocacaoAppService.recuperaAlocacoesERelacionados();

                    for (Alocacao alocacao : alocacoes) {
                        System.out.println('\n' +
                                "Número = " + alocacao.getId() +
                                "    Data Entrada = " + alocacao.getDataEntrada() +
                                "    Data Saída = " + alocacao.getDataSaida() +
                                "    Id presidiário = " + alocacao.getPresidiario().getId() +
                                "    Nome presidiário = " + alocacao.getPresidiario().getNome() +
                                "    Id cela = " + alocacao.getCela().getId() +
                                "    Versão = " + alocacao.getVersao());
                    }

                    break;
                }

                case 0: {
                    return 0;
                }

                case 5:
                    return 5;

                default:
                    System.out.println('\n' + "Opção inválida!");
            }
        } while (true);
    }

    public static int chamaCela() throws CelaNaoEncontradaException {
        CelaAppService celaAppService = (CelaAppService) fabrica.getBean("celaAppService");

        PrisaoAppService prisaoAppService = (PrisaoAppService) fabrica.getBean("prisaoAppService");
        Prisao prisao;
        int opcaoCela = 0;
        int capacidade;
        String tipo;
        Cela novaCela;
        do {
            System.out.println('\n' + "As seguintes operações podem ser feitas:");
            System.out.println('\n' + "1. Cadastrar uma Cela");
            System.out.println("2. Alterar uma cela");
            System.out.println("3. Remover uma cela");
            System.out.println("4. Listar todas as celas");
            System.out.println("5. Voltar ao menu anterior");
            System.out.println("0. Sair");

            opcaoCela = Console.readInt('\n' +
                    "Digite um número entre 0 e 5:");

            switch (opcaoCela) {
                case 1: {
                    capacidade = Integer.parseInt(Console.readLine('\n' +
                            "Informe a capacidade da cela: "));
                    tipo = Console.readLine(
                            "Informe o tipo de cela: ");

                    long idPrisao = parseLong(Console.readLine(
                            "Informe o id da prisão: "));

                    try {
                        prisao = prisaoAppService.recuperaPrisao(idPrisao);
                    } catch (PrisaoNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    novaCela = new Cela(capacidade, tipo, prisao);

                    long numero = celaAppService.inclui(novaCela);

                    System.out.println('\n' + "Cela número " +
                            numero + " incluído com sucesso!");

                    break;
                }

                case 2: {
                    int resposta = Console.readInt('\n' +
                            "Digite o número da cela que você deseja alterar: ");

                    try {
                        novaCela = celaAppService.recuperaCela(resposta);
                    } catch (CelaNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' +
                            "Número = " + novaCela.getId() +
                            "    Capacidade = " + novaCela.getCapacidade() +
                            "    Tipo = " + novaCela.getTipo() +
                            "    Id prisão = " + novaCela.getPrisao().getId() +
                            "    Versão = " + novaCela.getVersao());

                    System.out.println('\n' + "O que você deseja alterar?");
                    System.out.println('\n' + "1. Capacidade");
                    System.out.println('\n' + "2. Tipo ");
                    System.out.println('\n' + "3. Id prisão ");

                    int opcaoAlteracao = Console.readInt('\n' +
                            "Digite um número de 1 a 3:");

                    switch (opcaoAlteracao) {
                        case 1:
                            int novaCapacidade = Integer.parseInt(Console.
                                    readLine("Digite a nova capacidade: "));

                            novaCela.setCapacidade(novaCapacidade);

                            try {
                                celaAppService.altera(novaCela);

                                System.out.println('\n' +
                                        "Alteração de nome efetuada com sucesso!");
                            } catch (CelaNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;

                        case 2:
                            String novoTipo = Console.
                                    readLine("Digite o novo tipo: ");

                            novaCela.setTipo(novoTipo);

                            try {
                                celaAppService.altera(novaCela);

                                System.out.println('\n' +
                                        "Alteração do tipo feita " +
                                        "com sucesso!");
                            } catch (CelaNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;
                        case 3:
                            long novoIdPrisao = parseLong(Console.
                                    readLine("Digite o novo id prisão: "));

                            try {
                                prisao = prisaoAppService.recuperaPrisao(novoIdPrisao);
                            } catch (PrisaoNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                                break;
                            }
                            novaCela.setPrisao(prisao);

                            try {
                                celaAppService.altera(novaCela);

                                System.out.println('\n' +
                                        "Alteração do id prisão feita " +
                                        "com sucesso!");
                            } catch (CelaNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;

                        default:
                            System.out.println('\n' + "Opção inválida!");
                    }

                    break;
                }

                case 3: {
                    int resposta = Console.readInt('\n' +
                            "Digite o número da cela que você deseja remover: ");

                    try {
                        novaCela = celaAppService.recuperaCela(resposta);
                    } catch (CelaNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' +
                            "Número = " + novaCela.getId() +
                            "    Capacidade = " + novaCela.getCapacidade() +
                            "    Tipo = " + novaCela.getTipo() +
                            "    Id prisão = " + novaCela.getPrisao().getId() +
                            "    Endereço = " + novaCela.getPrisao().getEndereco() +
                            "    Versão = " + novaCela.getVersao());

                    String resp = Console.readLine('\n' +
                            "Confirma a remoção da cela? (S/N) ");

                    if (resp.equals("s")) {
                        try {
                            celaAppService.exclui(novaCela);
                            System.out.println('\n' +
                                    "Cela removida com sucesso!");
                        } catch (CelaNaoEncontradaException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                    } else {
                        System.out.println('\n' +
                                "Cela não removida.");
                    }

                    break;
                }

                case 4: {
                    List<Cela> celas = celaAppService.recuperaCelas();

                    for (Cela cela : celas) {
                        System.out.println('\n' +
                                "Número = " + cela.getId() +
                                "    Capacidade = " + cela.getCapacidade() +
                                "    Tipo = " + cela.getTipo() +
                                "    Id prisão = " + cela.getPrisao().getId() +
                                "    Endereço = " + cela.getPrisao().getEndereco() +
                                "    Versão = " + cela.getVersao());
                    }

                    break;
                }

                case 0: {
                    return 0;
                }

                case 5:
                    return 5;

                default:
                    System.out.println('\n' + "Opção inválida!");
            }
        } while (true);
    }

    public static int chamaPrisao() throws PrisaoNaoEncontradaException {
        PrisaoAppService prisaoAppService = (PrisaoAppService) fabrica.getBean("prisaoAppService");

        int opcaoPrisao = 0;
        int capacidade;
        String endereco;
        String nivelSeguranca;
        Prisao novaPrisao;
        do {
            System.out.println('\n' + "As seguintes operações podem ser feitas:");
            System.out.println('\n' + "1. Cadastrar uma Prisao");
            System.out.println("2. Alterar uma prisao");
            System.out.println("3. Remover uma prisao");
            System.out.println("4. Listar todas as prisoes");
            System.out.println("5. Voltar ao menu anterior");
            System.out.println("0. Sair");

            opcaoPrisao = Console.readInt('\n' +
                    "Digite um número entre 0 e 5:");

            switch (opcaoPrisao) {
                case 1: {
                    capacidade = Integer.parseInt(Console.readLine('\n' +
                            "Informe a capacidade da prisao: "));
                    endereco = Console.readLine(
                            "Informe o endereço da prisão: ");
                    nivelSeguranca = Console.readLine(
                            "Informe o nível de segurança da prisão: ");
                    novaPrisao = new Prisao(capacidade, endereco, nivelSeguranca);

                    long numero = prisaoAppService.inclui(novaPrisao);

                    System.out.println('\n' + "Prisão número " +
                            numero + " incluído com sucesso!");

                    break;
                }

                case 2: {
                    int resposta = Console.readInt('\n' +
                            "Digite o número da prisao que você deseja alterar: ");

                    try {
                        novaPrisao = prisaoAppService.recuperaPrisao(resposta);
                    } catch (PrisaoNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' +
                            "Número = " + novaPrisao.getId() +
                            "    Capacidade = " + novaPrisao.getCapacidade() +
                            "    Endereço = " + novaPrisao.getEndereco() +
                            "    Nível de Segurança = " + novaPrisao.getNivelSeguranca() +
                            "    Versão = " + novaPrisao.getVersao());

                    System.out.println('\n' + "O que você deseja alterar?");
                    System.out.println('\n' + "1. Capacidade");
                    System.out.println("2. Endereço ");
                    System.out.println("3. Nível de Segurança ");


                    int opcaoAlteracao = Console.readInt('\n' +
                            "Digite um número de 1 a 3:");

                    switch (opcaoAlteracao) {
                        case 1:
                            int novaCapacidade = Integer.parseInt(Console.
                                    readLine("Digite a nova capacidade: "));

                            novaPrisao.setCapacidade(novaCapacidade);

                            try {
                                prisaoAppService.altera(novaPrisao);

                                System.out.println('\n' +
                                        "Alteração de nome efetuada com sucesso!");
                            } catch (PrisaoNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;

                        case 2:
                            String novoEndereco = Console.
                                    readLine("Digite o novo endereço: ");

                            novaPrisao.setEndereco(novoEndereco);

                            try {
                                prisaoAppService.altera(novaPrisao);

                                System.out.println('\n' +
                                        "Alteração do endereço feita " +
                                        "com sucesso!");
                            } catch (PrisaoNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;

                        case 3:
                            String novoNivel = Console.
                                    readLine("Digite o novo nível de segurança: ");

                            novaPrisao.setNivelSeguranca(novoNivel);

                            try {
                                prisaoAppService.altera(novaPrisao);

                                System.out.println('\n' +
                                        "Alteração do nível de segurança feita " +
                                        "com sucesso!");
                            } catch (PrisaoNaoEncontradaException e) {
                                System.out.println('\n' + e.getMessage());
                            } catch (EstadoDeObjetoObsoletoException e) {
                                System.out.println('\n' + "A operação não foi " +
                                        "efetuada: os dados que você " +
                                        "tentou salvar foram modificados " +
                                        "por outro usuário.");
                            }

                            break;

                        default:
                            System.out.println('\n' + "Opção inválida!");
                    }

                    break;
                }

                case 3: {
                    int resposta = Console.readInt('\n' +
                            "Digite o número da prisao que você deseja remover: ");

                    try {
                        novaPrisao = prisaoAppService.recuperaPrisao(resposta);
                    } catch (PrisaoNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    System.out.println('\n' +
                            "Número = " + novaPrisao.getId() +
                            "    Capacidade = " + novaPrisao.getCapacidade() +
                            "    Endereço = " + novaPrisao.getEndereco() +
                            "    Nível de Segurança = " + novaPrisao.getNivelSeguranca() +
                            "    Versão = " + novaPrisao.getVersao());

                    String resp = Console.readLine('\n' +
                            "Confirma a remoção da prisao? (S/N) ");

                    if (resp.equals("s")) {
                        try {
                            prisaoAppService.exclui(novaPrisao);
                            System.out.println('\n' +
                                    "Prisao removida com sucesso!");
                        } catch (PrisaoNaoEncontradaException e) {
                            System.out.println('\n' + e.getMessage());
                        }
                    } else {
                        System.out.println('\n' +
                                "Prisão não removido.");
                    }

                    break;
                }

                case 4: {
                    List<Prisao> prisoes = prisaoAppService.recuperaPrisoes();

                    for (Prisao prisao : prisoes) {
                        System.out.println('\n' +
                                "Número = " + prisao.getId() +
                                "    Capacidade = " + prisao.getCapacidade() +
                                "    Endereço = " + prisao.getEndereco() +
                                "    Nível de Segurança = " + prisao.getNivelSeguranca() +
                                "    Versão = " + prisao.getVersao());
                    }

                    break;
                }

                case 0: {
                    return 0;
                }

                case 5:
                    return 5;

                default:
                    System.out.println('\n' + "Opção inválida!");
            }
        } while (true);
    }

    public static void main(String[] args) throws PresidiarioNaoEncontradoException, AlocacaoNaoEncontradaException, CelaNaoEncontradaException, PrisaoNaoEncontradaException {

        LoginAppService loginAppService = (LoginAppService) fabrica.getBean("loginAppService");
        Usuario usuario;

        String conta = "";
        String senha = "";
        boolean logou = false;

        conta = Console.readLine("Informe seu login: ");
        senha = Console.readLine("Informe sua senha: ");
        usuario = loginAppService.logar(conta, senha);
        if (usuario != null) {
            System.out.println("Logado com sucesso.");
            logou = true;
        }
        while (!logou) {
            System.out.println("Ocorreu um erro ao fazer login, dados incorretos");

            conta = Console.readLine("Informe seu login: ");
            senha = Console.readLine("Informe sua senha: ");
            try {
                usuario = loginAppService.logar(conta, senha);
            } catch (Throwable e) {
                System.out.println('\n' + e.getMessage());
                break;
            }
            if (usuario != null) {
                System.out.println("Login efetuado com sucesso");
                logou = true;
            }
        }
        SingletonPerfis singletonPerfis = SingletonPerfis.getSingletonPerfis();

        String[] listaPerfis = new String[0];
        List<Perfil> perfis = new ArrayList<Perfil>();
        if (usuario != null) {
            perfis = usuario.getPerfis();
            listaPerfis = new String[perfis.size()];
        }
        int count = 0;
        for (Perfil p : perfis) {
            listaPerfis[count] = p.getPerfil();
            count ++;
        }
        singletonPerfis.setPerfis(listaPerfis);


        int opcao = 0;
        int n = 0;
        do {
            System.out.println('\n' + "Bem vindo ao Prison System!");
            System.out.println("Nosso sistema está passando por algumas mudanças, por isso, talvez, alguns recursos estejam indisponíveis.");
            System.out.println('\n' + "Escolha uma das opções abaixo");
            System.out.println('\n' + "1. Presidiário");
            System.out.println("2. Alocação");
            System.out.println("3. Cela");
            System.out.println("4. Prisão");
            System.out.println("0. Sair");

            opcao = Console.readInt('\n' +
                    "Digite um número entre 0 e 4:");

            switch (opcao) {
                case 1: {
                    n = chamaPresidiario();
                    break;
                }
                case 2: {
                    n = chamaAlocacao();
                    break;

                }
                case 3: {
                    n = chamaCela();
                    break;
                }
                case 4: {
                    n = chamaPrisao();
                    break;
                }
                case 0:
                    break;

            }

        } while (opcao != 0 && n != 0);
    }
}

