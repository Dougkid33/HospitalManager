# HospitalManager
Um CRUD de usuarios para gerenciamento de hospital e clinicas. Estudo em Java de toda a matéria de POO( Programação Orientada a Objetos)
As funcionalidades mínimas que deverão ser entregues são:
=>CRUD de PESSOA. Informações importantes: id, nome, endereço, CPF, telefone, login, senha, tipoUsuario, dataCriacao, dataModificacao.
Quem faz ? Todos podem criar uma pessoa e editar dados da pessoa.
Os usuários podem ser os donos da franquia, donos de uma unidade de franquia, administrativos, médicos e pacientes.
Existirá sempre um dono da franquia pré-cadastrado.
As pessoas podem se cadastrar automaticamente. Após o cadastro, as pessoas recebem o papel de paciente.
O dono da franquia e o dono da unidade podem modificar o estado de uma determinada pessoa.
Uma pessoa pode ser cadastrada com diferentes papéis. Cada login diferente irá diferenciar os usuários.
As atividades no software são sempre feitas com o usuário logado.
=>CRUD de MEDICO. Informações importantes: id, CRM, pessoa, especialidade, dataCriacao, dataModificacao.
Quem faz ? Donos da franquia e donos de uma unidade de franquia.
O médico tem apenas uma única especialidade.
O médico pode trabalhar em qualquer franquia.
=>CRUD de FRANQUIA. Informações importantes: id, nome, cnpj, cidade, endereço, responsavel (pessoa cadastrada), dataCriacao, dataModificacao. 
Quem faz ? Donos da franquia.
=>CRUD de UNIDADE da FRANQUIA. Informações importantes: id, franquia, cidade, endereço, responsavel (pessoa cadastrada), dataCriacao, dataModificacao. 
Quem faz ? Donos da franquia.
=>CRUD de CONSULTA. Informações importantes: id, dia e horário, estado (vazio, agendada, cancelada, realizada), medico, paciente, valor, unidade, dataCriacao, dataModificacao.
Quem faz ? Todos. Pacientes só podem ver as suas consultas.
O médico/paciente consegue visualizar informações a respeito das consultas.
O médico/paciente consegue visualizar informações a respeito dos procedimentos.
O médico consegue visualizar a sua agenda de consultas.
O médico consegue visualizar as consultas já realizadas por ele.
=>CRUD de INFO de CONSULTA. Um médico pode registrar informações da consulta e também buscar informações de consultas já feitas por ele. Informações importantes: id, consulta, descricao,  dataCriacao, dataModificacao.
Quem faz ? Médicos.
=>CRUD de PROCEDIMENTO. Informações importantes: id, nome,   consulta, dia e horário, estado (vazio, agendada, cancelada, realizada), valor, laudo, dataCriacao, dataModificacao.
O procedimento pode ser gerado a partir de um consulta ou a partir do interesse de um paciente.

Quem faz ? Médicos e pacientes (ver).
=>CRUD FINANCEIRO ADM.  Informações importantes: id, tipo movimento (entrada, saída), valor, unidade, descritivo movimento (consulta, procedimento, salário funcionário, energia, agua, pagamento franquia, ...), dataCriacao, dataModificacao.
Quem faz ? Donos da franquia, donos de uma unidade de franquia, administrativos.
A cada consulta/procedimento é gerado uma entrada para a unidade de  franquia.
=>CRUD de FINANCEIRO MÉDICO. Informações importantes: id, valor medico, estado (agendado, pago), franquia, dataCriacao, dataModificacao.
Todo dia 01 o software pesquisa todas as consultas e procedimentos feitos pelo médico no último mês e registra no software o montante total pago a ele.
O médico ganha 70% do valor da consulta. O médico 50% do valor do procedimento.
=>O software deve conter um calendário para o ano considerado. Todo dia 01, deverá ser pago o valor de R$1000,00 a administradora + 5% do faturamento total da clínica relativo a consultas e procedimentos.
Quem faz ? Donos da franquia, donos de uma unidade de franquia e  administrativos.
•	Relatórios
Gere um relatório com os dados financeiros mensais da FRANQUIA. Estes dados contem as entradas e saídas administrativas e as saídas de pagamentos ao médico.
Quem faz ? Dono da franquia

Gere um relatório com os dados financeiros mensais das UNIDADES. O relatório contém as entradas e saídas administrativas e as saídas de pagamentos aos médicos.
Quem faz ? Dono de uma unidade de franquia.

Gere um relatório de consultas e procedimentos de um dado paciente.
Quem faz ? Médicos e pacientes.

Gere um relatório dos valores recebidos pelo médico.
Quem faz ? Médico

•	Perfis
=> ADMINISTRADOR (logado)
Todos os privilégios do software. Esta pessoal tem o privilégio de dono da empresa.

=> Responsável pela Franquia (logado)
Todos os privilégios de uma dada franquia.

=> Administrativo (logado)
Gerencia consultas e procedimentos.

=> MÉDICO (logado)
Gerencia consultas e procedimentos. 

•	Observações
=> A aplicação será entregue em 2 etapas. 
-Não tem parte visual (javax.swing, JavaFX, ...) em nenhuma parte do trabalho.
-A parte 1 deverá ser entregue sem a utilização da parte visual (javax.swing, JavaFx, ...). Os mecanismos de entrada e saída serão: println, JOptionPane e Scanner. Não é permitido o uso de banco de dados. A aplicação deve ser completa. Recomenda-se armazenar os dados em memória (através da utilização do padrão DAO em memória) para facilitar testes. Não é permitido a utilização de LISTAS, MAPAS e CONJUNTOS.
-A parte 2 deverá adicionar um de banco de dados. O acesso ao banco de dados deve ser feito como ensinado em sala de aula. É permitido a utilização de LISTAS, MAPAS e CONJUNTOS.
=> Funcionalidades interessantes que podem ser adicionadas (não obrigatório):
-políticas de acesso
-geração de relatórios
-geração de gráficos
-framework para realização de persistência
-geração de boletos bancários
-acesso a arquivos
-agendamento de tarefas
