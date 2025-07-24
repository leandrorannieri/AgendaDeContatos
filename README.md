# 📒 AgendaContatos

Um programa simples em **Java** para cadastrar, salvar e carregar contatos em uma agenda usando arquivo texto.

---

## 🔍 Sobre o Projeto

Este projeto implementa uma agenda de contatos com funcionalidades básicas para:

- Cadastrar até 100 contatos
- Validar dados do contato (nome, telefone e email)
- Armazenar contatos em arquivo local (`contatos.txt`)
- Carregar contatos já salvos ao iniciar o programa
- Exibir a lista completa de contatos cadastrados

---

## ⚙️ Funcionalidades

- ✅ Cadastro de contatos com validação:
  - Nome obrigatório
  - Telefone com 10 ou 11 dígitos numéricos
  - Email com formato válido
- 💾 Salvamento automático dos contatos em arquivo texto
- 📂 Carregamento automático dos contatos existentes no arquivo
- 📋 Exibição dos contatos cadastrados ao finalizar o cadastro
- ✋ Possibilidade de interromper o cadastro a qualquer momento

---

## 🚀 Como Executar

1. **Compile os arquivos Java:**

```bash
javac AgendaContatos.java Contatos.java
