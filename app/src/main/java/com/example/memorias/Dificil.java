package com.example.memorias;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.example.memorias.databinding.FragmentDificilBinding;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dificil extends Fragment {

    private FragmentDificilBinding binding;
    int tags;
    int numero1 = 0, numero2 = 0;
    Button button1, button2;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Dificil() {
        // Required empty public constructor
    }

    public static Dificil newInstance(String param1, String param2) {
        Dificil fragment = new Dificil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDificilBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String nombre = SecondFragmentArgs.fromBundle(getArguments()).getNombre();
        String dificultad = SecondFragmentArgs.fromBundle(getArguments()).getDificultad();
        binding.txtnombre.setText("Hola "+nombre);
        // Generar y asignar valores a los botones
        List<Integer> randomValues = generateRandomValues();
        assignValuesToButtons(randomValues);

        // Asignar el click listener a cada botón
        binding.btn1.setOnClickListener(v -> Memoria(binding.btn1));
        binding.btn2.setOnClickListener(v -> Memoria(binding.btn2));
        binding.btn3.setOnClickListener(v -> Memoria(binding.btn3));
        binding.btn4.setOnClickListener(v -> Memoria(binding.btn4));
        binding.btn5.setOnClickListener(v -> Memoria(binding.btn5));
        binding.btn6.setOnClickListener(v -> Memoria(binding.btn6));
        binding.btn7.setOnClickListener(v -> Memoria(binding.btn7));
        binding.btn8.setOnClickListener(v -> Memoria(binding.btn8));
        binding.btn9.setOnClickListener(v -> Memoria(binding.btn9));
        binding.btn10.setOnClickListener(v -> Memoria(binding.btn10));
        binding.btn11.setOnClickListener(v -> Memoria(binding.btn11));
        binding.btn12.setOnClickListener(v -> Memoria(binding.btn12));
        binding.btn13.setOnClickListener(v -> Memoria(binding.btn13));
        binding.btn14.setOnClickListener(v -> Memoria(binding.btn14));
        binding.btn15.setOnClickListener(v -> Memoria(binding.btn15));
        binding.btn16.setOnClickListener(v -> Memoria(binding.btn16));
        binding.btn17.setOnClickListener(v -> Memoria(binding.btn17));
        binding.btn18.setOnClickListener(v -> Memoria(binding.btn18));
        binding.btn19.setOnClickListener(v -> Memoria(binding.btn19));
        binding.btn20.setOnClickListener(v -> Memoria(binding.btn20));

        binding.btnVolver.setOnClickListener(view1 -> {
            DificilDirections.ActionTercerFragmentoToFirstFragment2 action =
                    DificilDirections.actionTercerFragmentoToFirstFragment2(dificultad, nombre, tags);
            NavHostFragment.findNavController(Dificil.this).navigate(action);
        });
    }

    private void Memoria(Button button) {
        // Obtener el valor almacenado en el tag
        Integer value = (Integer) button.getTag();
        button.setText(String.valueOf(value));

        if (numero1 == 0) {
            numero1 = value;
            button1 = button;
            tags++;
        } else {
            numero2 = value;
            button2 = button;
            tags++;
            if (numero2 == numero1) {
                Toast.makeText(getActivity(), "Correcto", Toast.LENGTH_SHORT).show();
                // Deshabilitar los botones cuando se hace una coincidencia correcta
                button1.setEnabled(false);
                button2.setEnabled(false);
                numero1 = 0;
                numero2 = 0;
                verifcarBotones();
            } else {
                Toast.makeText(getActivity(), "Incorrecto", Toast.LENGTH_SHORT).show();
                // Usar Handler para retrasar la acción
                new Handler().postDelayed(() -> {
                    button1.setText("");
                    button2.setText("");
                }, 1000); // 1000 milisegundos = 1 segundo
                numero1 = 0;
                numero2 = 0;
            }
        }
    }

    private void verifcarBotones() {
        // Verificar si todos los botones están deshabilitados
        Button[] buttons = {
                binding.btn1, binding.btn2, binding.btn3,
                binding.btn4, binding.btn5, binding.btn6,
                binding.btn7, binding.btn8, binding.btn9,
                binding.btn10, binding.btn11, binding.btn12,
                binding.btn13,binding.btn14,binding.btn15,
                binding.btn16,binding.btn17,binding.btn18,
                binding.btn19,binding.btn20
        };

        boolean todosDeshabilitados = true; // Inicializar como verdadero

        for (Button btn : buttons) {
            if (btn.isEnabled()) {
                todosDeshabilitados = false; // Si algún botón está habilitado, cambiar a falso
                break;
            }
        }

        // Cambiar la visibilidad del botón volver si todos los botones están deshabilitados
        if (todosDeshabilitados) {
            binding.btnVolver.setVisibility(View.VISIBLE); // Mostrar el botón volver
        }
    }

    private void resetValues() {
        numero1 = 0;
        numero2 = 0;
        button1 = null;
        button2 = null;
    }

    private List<Integer> generateRandomValues() {
        List<Integer> values = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            values.add(i);
            values.add(i); // Añadir el número dos veces
        }
        Collections.shuffle(values); // Mezclar los valores aleatorios
        return values;
    }

    private void assignValuesToButtons(List<Integer> values) {
        // Asignar los valores generados a los botones
        Button[] buttons = {
                binding.btn1, binding.btn2, binding.btn3,
                binding.btn4, binding.btn5, binding.btn6,
                binding.btn7, binding.btn8, binding.btn9,
                binding.btn10, binding.btn11, binding.btn12,
                binding.btn13, binding.btn14, binding.btn15,
                binding.btn16, binding.btn17, binding.btn18,
                binding.btn19, binding.btn20
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setTag(values.get(i)); // Guardar el valor en el tag
            buttons[i].setText(""); // No mostrar el valor
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
