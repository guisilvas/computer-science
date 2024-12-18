using System;
using System.Collections;

namespace Program
{
    class List
    {
        static void Main(string[] args)
        {
            ArrayList al1 = new ArrayList();
            Console.WriteLine("ArrayList size: " + al1.Capacity);
            Console.WriteLine("ArrayList elements: " + al1.Count);

            al1.Add(1);

            Console.WriteLine("ArrayList size: " + al1.Capacity);
            Console.WriteLine("ArrayList elements: " + al1.Count);

            al1.Add(1);
            al1.Add(3);
            al1.Add(5);
            al1.Add('a');

            foreach(object o in al1)
            {
                Console.WriteLine(o);
            }
        }
    }
}