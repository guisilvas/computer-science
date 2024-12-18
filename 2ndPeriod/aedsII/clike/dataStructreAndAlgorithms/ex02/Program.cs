using System;
using System.Collections;

namespace Program
{
    class List
    {
        static void Main(string[] args)
        {
            int size = 5, value;
            double media = 0;
            ArrayList integerList = new ArrayList();
            for(int i = 0; i < size; ++i)
            {
                value = int.Parse(Console.ReadLine());
                integerList.Add(value);
                media += value;
            }
            media /= size;
            foreach(object o in integerList)
            {
                if( (int)o > media )
                {
                    Console.WriteLine(o);
                }
            }
        }
    }
}