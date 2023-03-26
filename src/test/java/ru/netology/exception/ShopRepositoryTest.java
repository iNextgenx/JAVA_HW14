package ru.netology.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    Product item1 = new Product(5, "Товар1", 100);
    Product item2 = new Product(12, "Товар2", 250);
    Product item3 = new Product(18, "Товар3", 300);
    Product item4 = new Product(55, "Товар4", 1000);
    Product item5 = new Product(63, "Товар5", 5000);


    @Test
    public void shouldTestFindByIdPosPositive() {  // Положительная проверка FindById

        ShopRepository repo = new ShopRepository();

        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.add(item5);

        Product expected = item2;
        Product actual = repo.findById(12);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldTestFindByIdPosNegative() { // Отрицательная проверка FindById

        ShopRepository repo = new ShopRepository();

        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.add(item5);

        Product expected = null;
        Product actual = repo.findById(3);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldTestRemoveByIdExists() { // Тест удаления существующего элемента

        ShopRepository repo = new ShopRepository();

        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.add(item5);
        repo.removeById(18);

        Product[] expected = {item1, item2, item4, item5};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldTestRemoveByIdNotExists() { // Тест генерации NotFoundException

        ShopRepository repo = new ShopRepository();

        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.add(item5);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(999);
        });

    }

    @Test
    public void shouldTestAddWhenExists(){ // Тест генерации AlreadyExistsException

        ShopRepository repo = new ShopRepository();

        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.add(item5);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(item3);
        });
    }

    @Test
    public void shouldTestAddWhenNotExists() { // Тест добавления элемента если такой не существует

        ShopRepository repo = new ShopRepository();

        repo.add(item2);
        repo.add(item1);

        Product[] expected = {item2, item1};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

}
