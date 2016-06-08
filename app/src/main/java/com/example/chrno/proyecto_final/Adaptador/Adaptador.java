package com.example.chrno.proyecto_final.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chrno.proyecto_final.R;
import com.example.chrno.proyecto_final.clases.Deseo;
import com.example.chrno.proyecto_final.clases.ListaDeseo;
import com.example.chrno.proyecto_final.clases.Producto;
import com.example.chrno.proyecto_final.clases.Usuario;
import com.example.chrno.proyecto_final.metodos.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Adaptador extends ArrayAdapter {

    private Context ctx;
    private int res;
    private LayoutInflater lInflator;
    private List valores;
    private int tipo;
    private Producto product;
    static class ViewHolder {
        public TextView tv1, tv2, tvDesc,tvNombreLista;
        public ImageView ivCake,ivUsuario, ivProducto;
    }

    public Adaptador(Context context, int resource, List objects, int tipo) {
        super(context, resource, objects);
        this.ctx = context;//actividad
        this.res = resource;//layout del item
        this.valores = objects;//lista de valores
        this.lInflator = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        this.tipo=tipo;
        System.out.println("TIPO: "+tipo);
    }

    public boolean borrar(int position) {
        try {
            valores.remove(position);
            this.notifyDataSetChanged();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1
        ViewHolder gv = new ViewHolder();
        if(convertView==null){
            convertView = lInflator.inflate(res, null);
            if(tipo==0){
                gv.tvNombreLista=(TextView)convertView.findViewById(R.id.tvWishListItem);

            }
            if(tipo==1){
                TextView tv = (TextView) convertView.findViewById(R.id.tvWishItemNameItem);
                gv.tv1 = tv;
                tv = (TextView) convertView.findViewById(R.id.tvWishAnotationItem);
                gv.tvDesc = tv;
                tv = (TextView) convertView.findViewById(R.id.tvWishLevelItem);
                gv.tv2 =tv;
            }
            if(tipo==2){
                TextView tv = (TextView) convertView.findViewById(R.id.tvProductNameItem);
                gv.tv1 = tv;
                tv = (TextView) convertView.findViewById(R.id.tvLikeProductItem);
                gv.tv2 = tv;
                gv.ivProducto=(ImageView) convertView.findViewById(R.id.imageView12);
            }
            if(tipo==3){
                TextView tv = (TextView) convertView.findViewById(R.id.tv);
                gv.tv1 = tv;
                tv = (TextView) convertView.findViewById(R.id.tv2);
                gv.tv2 = tv;
                gv.ivCake = (ImageView)convertView.findViewById(R.id.ivBirthDay);
                gv.ivUsuario=(ImageView)convertView.findViewById(R.id.imageView3);
            }
            convertView.setTag(gv);
        } else {
            gv = (ViewHolder) convertView.getTag();
        }

        switch (tipo){
            case 0:
                ListaDeseo ld= (ListaDeseo) valores.get(position);
                gv.tvNombreLista.setText(ld.getNombre());
                break;

            case 1:
                Deseo d= (Deseo) valores.get(position);
                List<Producto> listaProductos= Util.sacaProductos(getContext(), 0);
                for(Producto p : listaProductos) {
                    if (p.getId() == d.getIdProducto()) {
                        product = p;
                    }
                }
                gv.tv1.setText(product.getNombre());
                gv.tvDesc.setText(product.getDescripcion());
                gv.tv2.setText(d.getFechaCreacion()+"");
                break;

            case 2:
                Producto p=(Producto) valores.get(position);
                gv.tv1.setText(p.getNombre());
                gv.tv2.setText("Likes: "+p.getLikes());
                gv.ivProducto.setImageBitmap(p.getBm());
                break;

            case 3:
                Usuario amigo = (Usuario) valores.get(position);
                gv.tv1.setText(amigo.getNombre() + " " +amigo.getApellidos());
                gv.tv2.setText(amigo.getEmail());
                gv.ivUsuario.setImageBitmap(amigo.getBmImage());
                if(isCloseToBirthday(amigo.getFechaNacimiento())){
                    gv.ivCake.setVisibility(View.VISIBLE);
                } else {
                    gv.ivCake.setVisibility(View.GONE);
                }
//                gv.ivFriend.setImageURI(amigo.getUriImage());
                break;
            default:
                gv.tv1.setText("???????");
        }

        return convertView;
    }

    public boolean isCloseToBirthday(Date Birthday){
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        long diff = Birthday.getTime() - new Date().getTime();
        System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        if (diff < 7) {
            return true;
        }
        return false;
    }
}
