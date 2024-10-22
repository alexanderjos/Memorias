package com.example.memorias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.memorias.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnIniciarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = binding.edtNombre.getText().toString();
                if(!nombre.isEmpty()||!nombre.equals("")){
                    int select = binding.rgSeleccion.getCheckedRadioButtonId();
                    if (select != -1) {
                        RadioButton selectRadio = binding.getRoot().findViewById(select);
                        String textoSelecionado = selectRadio.getText().toString();
                        if (textoSelecionado.equals("Facil")){
                            FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                                    FirstFragmentDirections.actionFirstFragmentToSecondFragment(nombre,textoSelecionado);
                            NavHostFragment.findNavController(FirstFragment.this).navigate(action);
                        }else {
                            FirstFragmentDirections.ActionFirstFragmentToTercerFragmento action1 =
                                    FirstFragmentDirections.actionFirstFragmentToTercerFragmento(nombre,textoSelecionado);
                            NavHostFragment.findNavController(FirstFragment.this).navigate(action1);
                        }
                    }else {
                        Toast.makeText(getActivity(), "Selecciona una opción", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "Ingrese un nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });




        binding.btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Verificamos si el argumento "dificultad" está presente
                if (!getArguments().containsKey("DificultadP1")||!getArguments().containsKey("NombreP1")||!getArguments().containsKey("TagsP1")) {
                    Toast.makeText(getActivity(), "Todavia no ha jugado", Toast.LENGTH_SHORT).show();
                } else {
                    String dificultad = FirstFragmentArgs.fromBundle(getArguments()).getDificultadP1();
                    String nombreRecibido = FirstFragmentArgs.fromBundle(getArguments()).getNombreP1();
                    int tags = FirstFragmentArgs.fromBundle(getArguments()).getTagsP1();
                    FirstFragmentDirections.ActionFirstFragmentToCuartoFragmento action3 =
                            FirstFragmentDirections.actionFirstFragmentToCuartoFragmento(nombreRecibido,dificultad,tags);
                    NavHostFragment.findNavController(FirstFragment.this).navigate(action3);

                }



                // Aquí puedes continuar con tu lógica utilizando dificultad
            }
        });







    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}