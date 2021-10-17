package com.example.newspaper.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newspaper.R
import com.example.newspaper.models.Article
import com.example.newspaper.ui.ArticleActivity
import kotlinx.android.synthetic.main.item_article.view.*

class NewsAdapter(var context: Context) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            if (article.urlToImage == null || article.urlToImage == "null"){
                Glide.with(this).load(R.drawable.news).into(ivArticleImage)
            }else{
                Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            }
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt

        }
        holder.itemView.setOnClickListener {
            //start article acticty
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra("url", article.url)
            intent.putExtra("title", article.title)
            context.startActivity(intent)
        }
    }


}

