# HospitalManager
# Sistema de Gerenciamento Hospitalar - CRUD de Usuários

Um sistema de gerenciamento hospitalar destinado ao controle de hospitais e clínicas, implementado em Java com ênfase nos conceitos de Programação Orientada a Objetos (POO). O sistema permite o gerenciamento de usuários, médicos, consultas, procedimentos, finanças e geração de relatórios.

## Funcionalidades Mínimas

### CRUD de Pessoa

O CRUD de Pessoa permite a gestão dos seguintes atributos: id, nome, endereço, CPF, telefone, login, senha, tipo de usuário, data de criação e data de modificação. Os usuários com diferentes papéis (donos da franquia, donos de unidade, administrativos, médicos e pacientes) podem criar e editar pessoas. Uma pessoa pode ser cadastrada com múltiplos papéis.

### CRUD de Médico

O CRUD de Médico é destinado aos donos da franquia e donos de unidades. Ele abrange informações como id, CRM, pessoa associada, especialidade, data de criação e data de modificação. Um médico possui uma única especialidade e pode trabalhar em qualquer unidade da franquia.

### CRUD de Franquia

O CRUD de Franquia é restrito aos donos da franquia e permite a gestão de informações como id, nome, CNPJ, cidade, endereço, pessoa responsável, data de criação e data de modificação.

### CRUD de Unidade da Franquia

Similar ao CRUD de Franquia, o CRUD de Unidade da Franquia permite a gestão de unidades específicas. Os donos da franquia têm acesso a essa funcionalidade.

### CRUD de Consulta

O CRUD de Consulta é acessível a todos os usuários. Pacientes só podem visualizar suas próprias consultas. Médicos e pacientes podem ver detalhes e informações sobre consultas e procedimentos.

### CRUD de Informações de Consulta

Apenas médicos têm acesso ao CRUD de Informações de Consulta. Permite registrar e buscar informações relacionadas às consultas realizadas por eles.

### CRUD de Procedimento

O CRUD de Procedimento, acessível por médicos e pacientes (apenas para visualização), abrange informações como id, nome, consulta associada, dia e horário, estado, valor, laudo, data de criação e data de modificação.

### CRUD Financeiro Administrativo

Destinado a donos da franquia, donos de unidades e administrativos, o CRUD Financeiro Administrativo trata de movimentações financeiras, incluindo tipo de movimento, valor, unidade associada, descrição do movimento, data de criação e data de modificação. Entradas são geradas a partir de consultas/procedimentos.

### CRUD Financeiro Médico

Os médicos podem acessar o CRUD Financeiro Médico. O sistema registra automaticamente o montante total pago ao médico, calculado com base nas consultas e procedimentos realizados por ele.

### Calendário

O software inclui um calendário anual para auxiliar no planejamento.

### Geração de Relatórios

- Relatório de Dados Financeiros Mensais da Franquia: Detalha entradas e saídas administrativas e pagamentos a médicos.
- Relatório de Dados Financeiros Mensais das Unidades: Inclui entradas, saídas administrativas e pagamentos a médicos.
- Relatório de Consultas e Procedimentos por Paciente: Médicos e pacientes podem gerar relatórios.
- Relatório de Valores Recebidos por Médico: Apenas médicos têm acesso.

## Perfis de Usuário

- **Administrador:** Possui todos os privilégios do software e atua como dono da empresa.
- **Responsável pela Franquia:** Tem privilégios equivalentes aos de uma franquia específica.
- **Administrativo:** Gerencia consultas e procedimentos.
- **Médico:** Gerencia consultas, procedimentos e finanças relacionadas.

Este sistema foi desenvolvido como um estudo em Java, com foco em POO, para atender às necessidades de gestão de hospitais e clínicas. Ele permite uma gestão abrangente e eficiente, respeitando os diferentes papéis e responsabilidades dos usuários envolvidos.

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
