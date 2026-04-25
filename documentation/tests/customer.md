# Cenários de Testes (BDD)

## POST /customer

### Validação de Campos Obrigatórios

#### Caso 1: Criar cliente com todos os campos válidos
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com todos os campos válidos
Então a resposta deve retornar status 201 Created
E o corpo da resposta deve conter os dados do cliente criado
E o campo salvo deve retornar true
E o header Location deve conter: /customer/{cpf-informado-no-request}
```

#### Caso 2: Criar cliente sem CPF
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer sem o campo CPF
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o CPF é obrigatório
```

#### Caso 3: Criar cliente sem Nome
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer sem o campo Nome
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o Nome é obrigatório
```

#### Caso 4: Criar cliente sem CEP
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer sem o campo CEP
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o CEP é obrigatório
```

#### Caso 5: Criar cliente sem Endereço
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer sem o campo Endereço
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o Endereço é obrigatório
```

#### Caso 6: Criar cliente sem Gênero
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer sem o campo Gênero
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o Gênero é obrigatório
```

### Validação do CPF

#### Caso 7: CPF com formato inválido (sem pontos e traço)
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com CPF no formato inválido (sem pontos e traço)
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o CPF deve estar no formato xxx.xxx.xxx-xx
```

#### Caso 8: CPF com todos os dígitos iguais
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com CPF inválido com todos os dígitos iguais
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o CPF não pode conter todos os dígitos iguais
```

#### Caso 9: CPF com dígitos verificadores inválidos
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com CPF com dígitos verificadores inválidos
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o CPF informado é inválido
```

#### Caso 10: CPF com letras
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com CPF contendo letras
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o CPF deve estar no formato xxx.xxx.xxx-xx
```

#### Caso 11: CPF com pontos e hífen em posição errada
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com CPF com pontos e hífen em posição errada
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o CPF deve estar no formato xxx.xxx.xxx-xx
```

### Validação do CEP

#### Caso 12: CEP com formato inválido (sem hífen)
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com CEP no formato inválido (sem hífen)
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o CEP deve estar no formato xxxxx-xxx
```

#### Caso 13: CEP com hífen em posição errada
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com CEP com hífen em posição errada
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o CEP deve estar no formato xxxxx-xxx
```

#### Caso 14: CEP com caracteres não numéricos
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com CEP contendo caracteres não numéricos
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o CEP deve estar no formato xxxxx-xxx
```

### Validação de Gênero

#### Caso 15: Gênero válido - Masculino
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com gênero "masculino"
Então a resposta deve retornar status 201 Created
E o cliente deve ser criado com sucesso
```

#### Caso 16: Gênero válido - Feminino
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com gênero "feminino"
Então a resposta deve retornar status 201 Created
E o cliente deve ser criado com sucesso
```

#### Caso 17: Gênero válido - Não informado
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com gênero "não informado"
Então a resposta deve retornar status 201 Created
E o cliente deve ser criado com sucesso
```

#### Caso 18: Gênero inválido
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com gênero inválido
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o gênero deve ser: masculino, feminino ou não informado
```

### Validação do Nome

#### Caso 19: Nome com mais de 255 caracteres
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com nome contendo mais de 255 caracteres
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o nome não pode ter mais de 255 caracteres
```

#### Caso 20: Nome com exatamente 255 caracteres
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com nome contendo exatamente 255 caracteres
Então a resposta deve retornar status 201 Created
E o cliente deve ser criado com sucesso
```

#### Caso 21: Nome vazio
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com nome vazio
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o Nome é obrigatório
```

#### Caso 22: Nome informado como null
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com nome informado como null
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o Nome é obrigatório
```

### Validação do Endereço

#### Caso 23: Endereço com mais de 255 caracteres
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com endereço contendo mais de 255 caracteres
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o endereço não pode ter mais de 255 caracteres
```

#### Caso 24: Endereço com exatamente 255 caracteres
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com endereço contendo exatamente 255 caracteres
Então a resposta deve retornar status 201 Created
E o cliente deve ser criado com sucesso
```

#### Caso 25: Endereço vazio
```gherkin
Dado que um cliente deseja criar uma nova conta
Quando enviar um POST para /customer com endereço vazio
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o Endereço é obrigatório
```

## GET /customer/{cpf}

### Validação de Consulta

#### Caso 1: Consultar cliente existente por CPF
```gherkin
Dado que existe um cliente cadastrado no sistema
Quando enviar um GET para /customer/{cpf} com um CPF válido
Então a resposta deve retornar status 200 OK
E o corpo da resposta deve conter os dados completos do cliente
```

#### Caso 2: Consultar cliente inexistente
```gherkin
Dado que não existe um cliente cadastrado com um determinado CPF
Quando enviar um GET para /customer/{cpf} com um CPF que não existe
Então a resposta deve retornar status 404 Not Found
E a mensagem de erro deve indicar que o cliente não foi encontrado
```

#### Caso 3: Consultar com CPF em formato inválido
```gherkin
Dado que um cliente deseja consultar um cliente
Quando enviar um GET para /customer/{cpf} com CPF em formato inválido
Então a resposta deve retornar status 400 Bad Request
E a mensagem de erro deve indicar que o CPF deve estar no formato xxx.xxx.xxx-xx
```