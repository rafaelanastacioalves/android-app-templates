# desafio-mobicare

## Descrição Geral

Desafio tecnico da empresa Mobicare.

![tela de listagem](captures/Screenshot_1523145822.png)

![tela de detalhes](captures/Screenshot_1523145868.png)

## Descrição técnica

Utilizei as seguintes bibliotecas:
- AsyncTaskLoader: para servir de meio para a lógica das requisições de API e transformação no modelo de dados a ser retornardo pelas activitys. Esta solução fornecida pelo Google leva em conta os ciclos de atividade das Activitys e Fragments e, assim, evita problemas potenciais relacionados a depserdício de memória, etc.
- Espresso: para reazação de testes insturmentados;
- Retrofit: para consulta de API;
- support.v4: Para retrocompatibilidade e adicionar animação de transição - neste caso, API versão 21 para cima.
- Mockwebserver: para mocar as respostas nos testes intrumentados.


## Testes

Os testes utilizam o buildtype ```instrumentation``` para rodar, pois forçamos que o app seja buildado para apontar para localhost durante os testes.
