package com.insidedeveloper.siie;

public class Validaciones {

    public boolean ValidarEntero(String Entrada)
    {
        try
        {
            Integer.parseInt(Entrada);
            return true;

        }
        catch(Exception e)
        {
            System.out.println("La opción que ingreso es invalida");
            return false;
        }
    }

    public boolean ValidarEdad(String Entrada)
    {
        try
        {
            if(Integer.parseInt(Entrada)>-1)
                return true;

        }
        catch(Exception e)
        {
            System.out.println("La  edad que ingresaste no es valida");
            return false;
        }
        return false;
    }
    public boolean ValidarNombre(String Entrada)
    {
        char[] arreglo = Entrada.toCharArray();

        for(int i=0; i<arreglo.length; i++)
        {   int c=arreglo[i];
            if(!((c>= 65 && c <= 90) || (c >= 97 && c<= 122) || c==164 ||  c==165 || c==32))
            {
                return false;
            }

        }
        return true;
    }

    public void Codigos(String e)
    {
        char c;
        for(int i=0; i<e.length(); i++)
        {
            c= e.charAt(i);
            System.out.print(c + "-" + (int)c + ",");
        }
    }

    public boolean ValidarTelefono(String Entrada)
    {
        char[] arreglo = Entrada.toCharArray();
        for(int i=0; i<arreglo.length; i++)
        {
            int c=arreglo[i];
            if(!((c >= 48 && c <= 57) || (c >= 40 && c <= 41) || c == 45 || c == 43 || c == 42 || c == 35))
            {
                return false;
            }
        }
        return true;
    }

    public boolean ValidarEstatura(String Entrada)
    {
        boolean Bandera = false;

        for(int i=0; i<Entrada.length(); i++)
        {
            int cod = Entrada.codePointAt(i);
            if(cod == 46)
            {
                if(Bandera == true){
                    return false;
                }

                Bandera = true;
            }
            else{
                if(!((cod >= 48 && cod <= 57) || cod == 46))
                    return false;
            }
        }
        return true;
    }

    public boolean ValidarCorreo(String Entrada)
    {
        boolean Bandera = false;
        char[] cod = Entrada.toCharArray();

        for(int i=0; i<cod.length; i++)
        {
            int c = cod[i];
            //int cod = Entrada.codePointAt(i);
            if(c == 64)
            {
                if(Bandera == true)
                {
                    return false;
                }
                Bandera = true;
            }
            else
            {
                if(!((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || (c >= 48 && c <= 57) || c == 64 || c == 45 || c == 46 || c == 95))
                    return false;
            }
        }
        return true;
    }

    public boolean ValidarDireccion(String Entrada)
    {
        char[] cod = Entrada.toCharArray();
        for(int i=0; i<cod.length; i++)
        {
            int c = cod[i];
            // cod = Entrada.codePointAt(i);
            if(!((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || (c >= 48 && c <= 57) || c == 35 || c == 165 || c == 164 || c == 32))
            {
                return false;
            }

        }
        return true;
    }
}
