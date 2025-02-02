package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {
  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    //удалил условие для пустого списка
    //пропускаем первый элемент через skip()
    return persons.stream()
        .skip(1)
        .map(Person::getFirstName)
        .toList();
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    //переделал стримы в обычное создание сета
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    //удалил длинную цепочку сравнений с null + убрал ненужную конкатенацию
    //испарвил повторения secondName
    return Stream.of(person.getFirstName(), person.getSecondName(), person.getMiddleName())
        .filter(Objects::nonNull)
        .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    //сделал создание мапы через стримы
    //раньше было некорретное создание мапа с "-1" в конструкторе
    return persons.stream()
        .collect(Collectors.toMap(Person::getId,
            this::convertPersonToString,
            (p1, p2) -> p1));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    //заменил громоздкий метод на вызов функции disjoint(...)
    return !Collections.disjoint(persons1, persons2);
  }

  //...
  //метод должен подсчитывать количество четных чисел в переданном списке
  public long countEven(Stream<Integer> numbers) {
    //убрал статическое поле count, т.к. она не имеет смысла
    //сделал все через стрим
    return numbers
        .filter(num -> num % 2 == 0)
        .count();
  }
}
