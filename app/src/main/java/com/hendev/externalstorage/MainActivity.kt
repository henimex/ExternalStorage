package com.hendev.externalstorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnWrite.setOnClickListener {
            writeExt()
        }

        btnRead.setOnClickListener {
            readExt()
        }

        btnDelete.setOnClickListener {
            deleteExt()
        }

        btnWrite2.setOnClickListener {
            writeInt()
        }

        btnRead2.setOnClickListener {
            readInt()
        }

        btnDelete2.setOnClickListener {
            deleteInt()
        }
    }

    fun writeInt() {
        try {
            val fo = openFileOutput("IntFile.txt", Context.MODE_PRIVATE)
            val _printer = OutputStreamWriter(fo)
            _printer.write(edtInput.text.toString())
            _printer.close()
            edtInput.setText("")

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readInt() {
        try {
            val fi = openFileInput("IntFile.txt")
            val isr = InputStreamReader(fi)
            val _reader = BufferedReader(isr)
            val sb = StringBuilder()
            var rows: String? = null

            while ({rows = _reader.readLine();rows}()!=null){
                sb.append(rows+"\n")
            }

            _reader.close()
            textView.text = sb.toString()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteInt() {
        val _dirrectory = filesDir
        val _file = File(_dirrectory,"IntFile.txt")
        _file.delete()
    }

    fun writeExt() {
        try {
            val _directory = applicationContext.getExternalFilesDir(null)?.absolutePath
            val _file = File(_directory, "MyFile.txt")

            if (!_file.exists()) {
                _file.createNewFile()
                Toast.makeText(this, "File Created", Toast.LENGTH_SHORT).show()
            }

            val fw = FileWriter(_file)
            val _printer = BufferedWriter(fw)
            _printer.write(edtInput.text.toString())
            _printer.flush()
            _printer.close()
            fw.close()
            edtInput.setText("")
            textView.setText("")
            Toast.makeText(this, "Input Added To Externel Storage", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readExt() {
        try {
            val _directory = applicationContext.getExternalFilesDir(null)?.absolutePath
            val _file = File(_directory, "MyFile.txt")

            val fr = FileReader(_file)
            val _reader = BufferedReader(fr)
            val sb = StringBuilder()
            var rows: String? = null

            while ({ rows = _reader.readLine();rows }() != null) {
                sb.append(rows + "\n")
            }
            _reader.close()
            textView.text = sb.toString()
            Toast.makeText(this, "File is Readable", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteExt() {
        try {
            val _directory = applicationContext.getExternalFilesDir(null)?.absolutePath
            val _file = File(_directory, "MyFile.txt")

            if (_file.exists()) {
                _file.delete()
                Toast.makeText(this, "File Deleted", Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}