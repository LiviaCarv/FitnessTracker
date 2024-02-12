  package com.example.fitnesstracker

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

  class MainActivity : AppCompatActivity() {
      private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                id=1,
                drawableId = R.drawable.baseline_auto_awesome_24,
                textStringId = R.string.label_imc,
                color = R.color.icons_green
            )
        )
        mainItems.add(
            MainItem(
                id=1,
                drawableId = R.drawable.baseline_assignment_turned_in_24,
                textStringId = R.string.label_tmb,
                color = Color.GRAY
            )
        )

        // utilizando objeto anonimo, que fica vivo enquanto o MainAdapter existir neste Contexto
        val adapter = MainAdapter(mainItems) { id ->
            when (id) {
                1 -> {
                    val intent = Intent(this@MainActivity, ImcActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    //outra atividade
                }
            }
        }
        rvMain = findViewById(R.id.rv_main)
        // conecta o adaptador à RecyclerView para que ele possa fornecer os dados e
        // gerenciar a exibição dos itens na lista.
        rvMain.adapter = adapter

        // responsável por organizar os itens em uma lista linear
        rvMain.layoutManager = GridLayoutManager(this, 2)

    }


      // Responsável por ligar os dados dinamicos aos elementos visuais exibidos no RV
      // <MainViewHolder> especifica o tipo de ViewHolder que este adapter irá usar.
      private inner class MainAdapter(private val mainItems: List<MainItem>, private val onItemClickListener: (Int) -> Unit) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
          // chamado quando o RecyclerView precisa criar uma nova instância de MainViewHolder.
          // infla o layout XML da célula específica que será usada para exibir os dados.
          override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
              val view = layoutInflater.inflate(R.layout.main_item, parent,false)
              return MainViewHolder(view)

          }

          // determinar quantos itens precisam ser exibidos.
          override fun getItemCount(): Int {
              return mainItems.size
          }

          // chamado quando o RecyclerView precisa exibir ou atualizar o conteúdo de uma célula específica.
          override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
              val currentItem = mainItems[position]
              holder.bind(currentItem)
          }

          // classe que define a célula individual dentro da RecyclerView, mas nao tem dados associados a ele
          // o RecyclerView que vai vincular os dados
          private inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
              fun bind(item: MainItem) {
                  val img = itemView.findViewById<ImageView>(R.id.item_img_icon)
                  val txt = itemView.findViewById<TextView>(R.id.item_txt_name)
                  val container: LinearLayout = itemView.findViewById(R.id.item_lyt_imc)

                  img.setImageResource(item.drawableId)
                  txt.setText(item.textStringId)
                  container.setBackgroundColor(item.color)

                  container.setOnClickListener{
                      onItemClickListener.invoke(item.id)
                  }
              }

          }

      }


  }