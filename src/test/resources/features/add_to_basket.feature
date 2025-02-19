Feature: Добавление товара в корзину

  Scenario Outline: Добавление товара в корзину со страницы найденных товаров
    Given пользователь открывает сайт "https://www.wildberries.ru/"
    When пользователь нажимает на "Фильтры"
    And пользователь наводит курсор на категорию "<Категория>"
    And пользователь нажимает на подкатегорию 1 "<Подкатегория 1>"
    And пользователь нажимает на подкатегорию 2 "<Подкатегория 2>"
    And пользователь нажимает на подкатегорию 3 "<Подкатегория 3>"
    And пользователь нажимает на кнопку "В корзину" у первого товара из списка
    Then пользователь должен перейти на страницу с "<Подкатегория 3>"
    And путь фильтра должен быть Главная - "<Категория>" - "<Подкатегория 1>" - "<Подкатегория 2>"
    And над логотипом "Корзина" в правом верхнем углу должна появиться красная цифра "1"

    When пользователь нажимает на "Корзина"
    Then текст и цена товара в корзине должны соответствовать названию и цене товара на странице
    And "Итого" должно быть равно сумме всех товаров
    And кнопка "Заказать" должна быть активна для нажатия

    Examples:
      | Категория        | Подкатегория 1   | Подкатегория 2            | Подкатегория 3            |
      | Бытовая техника  | Техника для дома | Пылесосы и пароочистители | Пылесосы и пароочистители |