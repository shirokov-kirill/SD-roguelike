# SD-roguelike

## Разработали
1. Широков Кирилл
2. Вавилов Марк
3. Энгель Игорь

## Общие сведения
1. Roguelike-игра для одного человека
2. Управление с помощью клавиатуры
3. Интерфейс на английском языке
4. Возможность загрузки карты из файла
5. Автоматическое появление монстров
6. Возможна поддержка сохранения игры

## Ключевые требования
1. 2 месяца на разработку
2. Необходима библиотека для отрисовки графики на консоли
3. Необходима поддержка "быстрой игры", то есть быстрого ввода пользователя
4. Персонаж должен перемещаться по карте с помощью клавиатуры
5. Монстры должны появляться не слишком близко к персонажу для комфортной игры
6. Игра создаётся максимально легко расширяемой 

## Роли и случаи использования
### Роли
1. Пользователь
2. Roguelike-фанат
2. Разработчик

### Случаи использования
1. Пользователь хочет отдохнуть. Необходимо предоставить ему понятный интерфейс и не перегруженную картинку в консоли
2. Roguelike-фанат хочет кастомизировать графику или добавить новые элементы. Для этого необходимо структурировать систему понятным любому человеку образом и в наиболее общем виде описать взаимодействия пользователя с игрой
3. Разработчик хочет внести изменения в отдельные модули системы, перегруппировать их, или добавить новые механики. Чтобы это сделать легко, необходимо сразу же подумать о расширяемости системы 