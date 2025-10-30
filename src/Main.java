import model.Book;

void main() {
    var book1 = new Book(1, "Sach1", "Tuan", 100);
    var book2 = new Book(2, "Sach2", "Tuan", 100);
    var book3 = new Book(3, "Sach3", "Tuan", 100);
    ArrayList<Book> bookList = new ArrayList<Book>();
    bookList.add(book1);
    bookList.add(book2);
    bookList.add(book3);
    IO.println(bookList);



}
