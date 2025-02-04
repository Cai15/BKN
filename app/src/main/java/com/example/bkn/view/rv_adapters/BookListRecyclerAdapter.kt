package com.example.bkn.view.rv_adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bkn.databinding.BookItemBinding
import com.example.bkn.domain.Book
import com.example.bkn.view.rv_viewholders.BookViewHolder


//в параметр передаем слушатель, чтобы мы потом могли обрабатывать нажатия из класса активити
class BookListRecyclerAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //Здесь у нас хранится список элементов для RV
    private val items = mutableListOf<Book>()

    //Этот метод нужно переопределить на возврат количества елементов в списке RV
    override fun getItemCount() = items.size

    //В этом методе мы привязываем наш view holder и передаем туда "надутую" верстку нашего фильма
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = BookViewHolder(
            BookItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return viewHolder
    }

    //В этом методе будет привзяка полей из объекта Book, к view из Book_item.xml
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //Проверяем какой у нас ViewHolder
        when (holder) {
            is BookViewHolder -> {
                //Вызываем метод bind(), который мы создали и передаем туда объект
                //из нашей базы данных с указанием позиции
                holder.bind(items[position])
                //Обрабатываем нажатие на весь элемент целиком(можно сделать на отдельный элемент
                //напрмер, картинку) и вызываем метод нашего листенера, который мы получаем из
                //конструктора адаптера
                holder.itemView.setOnClickListener {
                    clickListener.click(items[position])
                }
            }
        }
    }

    //Метод для добавления объектов в наш список


    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list: List<Book>) {
        //Сначала очишаем(если не реализовать DiffUtils)
        items.clear()
        //Добавляем
        items.addAll(list)
        //Уведомляем RV, что пришел новый список и ему нужно заново все "привязывать"
        notifyDataSetChanged()
    }

    //Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(Book: Book)
    }
}