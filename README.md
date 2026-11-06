# PDM — Roteiro 11 — Livraria

Continuação do Roteiro 10. O projeto mantém as funcionalidades de cadastro,
edição, listagem e persistência local com Room e foi refatorado para aplicar
princípios de Arquitetura Limpa e Injeção de Dependências com Hilt.

## 1. Arquitetura proposta antes da refatoração

A arquitetura foi organizada por funcionalidade (`autores` e `livros`) e,
dentro de cada funcionalidade, por responsabilidade:

```text
com.example.livraria0110
├── core
│   ├── database
│   ├── di
│   └── navigation
├── feature
│   ├── autores
│   │   ├── data
│   │   │   ├── local
│   │   │   ├── mapper
│   │   │   └── repository
│   │   ├── domain
│   │   │   ├── model
│   │   │   ├── repository
│   │   │   └── usecase
│   │   └── presentation
│   └── livros
│       ├── data
│       ├── domain
│       └── presentation
├── LivrariaApp.kt
└── MainActivity.kt
```

## 2. Análise crítica da arquitetura

A estrutura foi escolhida porque atende ao tamanho atual do projeto sem criar
módulos Gradle adicionais desnecessários. O isolamento acontece por pacotes e
interfaces:

- `domain` contém os modelos de negócio, contratos de repositório e casos de uso;
- `domain` não depende de Room, Compose, Activity, Context ou banco de dados;
- `data` conhece Room e implementa as interfaces definidas pelo domínio;
- `presentation` conhece os casos de uso e expõe estado para as telas Compose;
- `core/di` concentra as ligações de dependência feitas pelo Hilt;
- a UI não acessa DAO ou banco diretamente.

Essa organização segue a mesma ideia do projeto de referência apresentado em
aula, mas foi adaptada para as duas funcionalidades da Livraria.

## 3. Fluxo das dependências

```text
Compose
   ↓
ViewModel
   ↓
Use Case
   ↓
Repository (interface do domínio)
   ↓
RepositoryImpl (data)
   ↓
DAO / Room
```

O domínio permanece no centro e não conhece detalhes de infraestrutura.

## 4. Regras de negócio isoladas

Os casos de uso concentram validações que antes ficavam apenas na interface:

### Autor
- nome obrigatório;
- nacionalidade obrigatória.

### Livro
- título obrigatório;
- editora obrigatória;
- autor obrigatório;
- preço deve ser numérico e maior que zero.

## 5. Hilt

O Hilt é responsável por injetar:

- `LivrariaDatabase`;
- `AutorDao`;
- `LivroDao`;
- implementações dos repositórios;
- casos de uso;
- ViewModels.

Arquivos principais:

```text
LivrariaApp.kt
core/di/DatabaseModule.kt
core/di/RepositoryModule.kt
```

`MainActivity` utiliza `@AndroidEntryPoint` e os ViewModels utilizam
`@HiltViewModel`.

## 6. Funcionalidades preservadas do Roteiro 10

- lista de autores;
- lista de livros;
- cadastro e edição;
- barra de navegação;
- botão Cancelar;
- seleção de autor no cadastro de livro;
- persistência local Room/SQLite;
- loading nas listagens.

## 7. Compatibilidade usada

Para manter compatibilidade com o ambiente em que o Roteiro 10 já funcionou:

- Android Gradle Plugin: 8.13.2
- Gradle Wrapper: 8.13
- Kotlin: 2.0.21
- JDK do Gradle: 17
- compileSdk / targetSdk: 36
- Room: 2.6.1
- Hilt: 2.52
- Processadores: KAPT

## 8. Teste recomendado

1. Abra o projeto no Android Studio.
2. Configure **Gradle JDK = 17**.
3. Faça `Sync Project with Gradle Files`.
4. Execute `Build > Clean Project`.
5. Execute `Build > Assemble Project`.
6. Rode o aplicativo.
7. Cadastre um autor.
8. Cadastre um livro associado ao autor.
9. Feche e abra o aplicativo para confirmar a persistência.
10. Edite os registros e confirme que as alterações continuam salvas.

## Referência fornecida na atividade

Projeto de referência utilizado para análise arquitetural:
`borgess-dev/sgm-refac`.
