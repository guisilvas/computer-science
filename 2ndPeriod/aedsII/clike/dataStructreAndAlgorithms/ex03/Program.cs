// See https://aka.ms/new-console-template for more information
using System;
using System.Collections;

namespace ex03
{
    class ex03
    {
        public static void space()
        {
            Console.Write("--------------------");
            Console.WriteLine("\n\n");
        }

        public static void ReadObj(ArrayList al1)
        {
            Console.Write("Objects: ");
            foreach(object i in al1)
            {
                Console.Write(i + " ");
            }
            Console.WriteLine("\n");
        }

        public static void ReadObjArray(Object[] obj, int n)
        {
            Console.Write("Objects: ");
            foreach (Object item in obj)
            {
                Console.Write(item + " ");
            }
            space();
        }

        public static void AddObj(ArrayList al1, int n)
        {
            string obj;
            for(int i = 0; i < n; ++i)
            {
                Console.Write("Add " + (i+1) + "°: ");
                obj = Console.ReadLine();
                al1.Add(obj);
            }
            space();
        }

        public static void IndexObj(ArrayList al1)
        {
            string search;
            Console.Write("IndexOf object: ");
            search = Console.ReadLine();
            Console.WriteLine("Index: " + al1.IndexOf(search));
            space();
        }

        public static void LastIndexObj(ArrayList al1)
        {
            string search;
            Console.Write("LastIndexOf object: ");
            search = Console.ReadLine();
            Console.WriteLine("Index: " + al1.LastIndexOf(search));
            space();
        }
        
        public static void InsertObj(ArrayList al1)
        {
            string obj;
            int index;
            Console.WriteLine("Insert object");
            Console.Write("Index: ");
            index = int.Parse(Console.ReadLine());
            Console.Write("Object: ");
            obj = Console.ReadLine();
            al1.Insert(index, obj);
            space();
        }

        public static void RemoveAtObj(ArrayList al1)
        {
            int index;
            Console.Write("Remove object at index: ");
            index = int.Parse(Console.ReadLine());
            al1.RemoveAt(index);
            space();
        }

        public static void RemoveObj(ArrayList al1)
        {
            Object obj;
            Console.Write("Remove object: ");
            obj = Console.ReadLine();
            al1.Remove(obj);
            space();
        }

        public static void RemoveRangeObj(ArrayList al1)
        {
            int a, b;
            Console.WriteLine("Remove range: ");
            Console.Write("N° items: ");
            a = int.Parse(Console.ReadLine());
            Console.Write("Index initial: ");
            b = int.Parse(Console.ReadLine());
            al1.RemoveRange(a, b);
            space();
        }

        public static void ReverseObj(ArrayList al1)
        {
            Console.WriteLine("Reverse ArrayList");
            al1.Reverse();
            space();
        }

        public static void ReverseRangeObj(ArrayList al1)
        {
            int a, b;
            Console.WriteLine("Reverse Range ArrayList");
            Console.Write("N° items: ");
            a = int.Parse(Console.ReadLine());
            Console.Write("Index initial: ");
            b = int.Parse(Console.ReadLine());
            al1.Reverse(a, b);
            space();
        }

        public static void SortObj(ArrayList al1)
        {
            Console.WriteLine("Sort ArrayList");
            al1.Sort();
            space();
        }

        public static void ContainsObj(ArrayList al1)
        {
            string search;
            Console.Write("Contains in object: ");
            search = Console.ReadLine();
            al1.Contains(search);
            space();
        }

        public static void BinarySearchObj(ArrayList al1)
        {
            string search;
            Console.Write("Binary Search object: ");
            search = Console.ReadLine();
            Console.WriteLine(al1.BinarySearch(search));
            space();
        }

        public static void Main(string[] args)
        {
            int n;
            Object[] obj;
            ArrayList name = new ArrayList();

            Console.Write("Enter a size of arraylist: ");
            n = int.Parse(Console.ReadLine());
            obj = new object[n];

            AddObj(name, n);
            ReadObj(name);

            IndexObj(name);

            LastIndexObj(name);

            InsertObj(name);
            ReadObj(name);

            RemoveObj(name);
            ReadObj(name);

            RemoveAtObj(name);
            ReadObj(name);

            RemoveRangeObj(name);
            ReadObj(name);

            ReverseObj(name);
            ReadObj(name);

            SortObj(name);
            ReadObj(name);

            ReverseRangeObj(name);
            ReadObj(name);

            BinarySearchObj(name);
            ReadObj(name);

            obj = name.ToArray();
            ReadObjArray(obj, n);

            Console.WriteLine("Capacity: " + name.Capacity);

            //name.TrimToSize();
            //ReadObj(name);

            Console.WriteLine("Count: " + name.Count);

            ContainsObj(name);
            ReadObj(name);

            Console.WriteLine("Cleaning");
            name.Clear();
            ReadObj(name);
        }
    }
}
