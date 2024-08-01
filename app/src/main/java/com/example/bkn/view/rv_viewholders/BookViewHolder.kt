package com.example.bkn.view.rv_viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bkn.data.ApiConstants
import com.example.bkn.databinding.BookItemBinding
import com.example.bkn.data.Enity.Book


//В конструктор класс передается layout, который мы создали(Book_item.xml)
class BookViewHolder(binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {
    //Привязываем view из layout к переменным
    private val title = binding.title
    private val poster = binding.poster
    private val description = binding.description
   // private val ratingDonut = binding.ratingDonut

    //В этом методе кладем данные из Book в наши view
    fun bind(Book: Book) {
        //Устанавливаем заголовок
        title.text = Book.title
        //Устанавливаем постер
        //Указываем контейнер, в которм будет "жить" наша картинка
        Glide.with(itemView)
            //Загружаем сам ресурс
            .load(ApiConstants.IMAGES_URL + "w342" + Book.poster)
            //Центруем изображение
            .centerCrop()
            //Указываем ImageView, куда будем загружать изображение
            .into(poster)
        //Устанавливаем описание
        description.text = Book.description
    }
}