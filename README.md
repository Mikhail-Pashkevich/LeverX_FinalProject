# LeverX_FinalProject

## Задание

* Есть многоэтажное здание (этажность конфигурируема).
* В здании есть лифты (количество конфигурируемо).
* На каждом этаже есть кнопки вызова "вверх" и "вниз" (общие для всех лифтов).
* На каждом этаже рандомно появляются люди (рандомной массы), которые хотят ехать на другой этаж (рандомный).
* Интенсивность генерации людей конфигурируема. 
* У каждого лифта есть грузоподъемность, скорость и скорость открытия/закрытия дверей. 
* У человека есть масса тела и этаж на который ему нужно.
* Люди стоят в очереди на засадку в лифты (одна очередь вверх, одна вниз) не нарушая ее. Приехав на нужный этаж, человек исчезает. 

## Условия

* Необходимо реализовать непрерывно работающее приложение (люди появляются, вызывают лифт и едут на нужный этаж) используя многопоточность (Thread, wait, notify, sleep).
* По желанию можно использовать java.util.concurrent. Так же описать выбранный алгоритм текстом (кратко). 
* тесты, maven, логгирование
* stream API
* реализовать сбор статистики (сколько людей перевезено каждым лифтом с каким этажей и на какие этажи)
* логировать основные события системы (что бы по логам можно было следить за тем, что происходит)

## Алгоритм работы лифта

1. Если не активирован ни один этаж внутри лифта, в контроллере находится ближайший этаж к текущему, он активируется и лифт едет к этому этажу. Устанавливается состояние лифта = направлению, в которое он поедет после загрузки людей.
2. Определяем действие:
  1. Если состояние лифта = текущему => запускаются люди, которые "нажимают" на кнопки, тем самым добавляя активные этажи. Действя начинаются с начала.
  2. 
