Feature: Работа с фильтрами

  Scenario Outline: Применение фильтров
    Given пользователь открывает сайт "https://www.wildberries.ru/"
    When пользователь нажимает на "Фильтры"
    And пользователь наводит курсор на категорию "<Категория>"
    And пользователь нажимает на подкатегорию 1 "<Подкатегория 1>"
    And пользователь нажимает на подкатегорию 2 "<Подкатегория 2>"
    Then должна открыться страница "<Название страницы>"

    When пользователь нажимает на кнопку "Все фильтры"
    And пользователь применяет фильтр "Цена": "<Цена От>" и "<Цена До>"
    And пользователь применяет фильтр "Срок доставки": "<Срок доставки>"
    And пользователь применяет фильтр "Бренд": "<Бренд>"
    And пользователь применяет фильтр "Диагональ экрана": "<Диагональ экрана>"
    And пользователь нажимает на кнопку "Показать"
    Then фильтры "<Цена От>", "<Цена До>", "<Срок доставки>", "<Бренд>", "<Диагональ экрана>" должны отображаться на странице
    And на странице должна появиться кнопка "Сбросить все"
    And количество товара на странице должно быть равно количеству товара на странице фильтров
    And фильтры должны активироваться

    Examples:
      | Категория   | Подкатегория 1        | Подкатегория 2 | Название страницы     | Цена От  | Цена До  | Срок доставки | Бренд | Диагональ экрана |
      | Электроника | Ноутбуки и компьютеры | Ноутбуки       | Ноутбуки и ультрабуки | 100 000  | 149 000  | до 3 дней     | Apple | 13.3\"           |