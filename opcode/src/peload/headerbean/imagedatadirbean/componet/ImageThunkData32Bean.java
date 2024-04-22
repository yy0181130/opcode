package peload.headerbean.imagedatadirbean.componet;

import structs.DWord;
import structs.Word;

// typedef struct _IMAGE_THUNK_DATA32 {
// union {
// DWORD ForwarderString; // PBYTE
// DWORD Function; // PDWORD
// DWORD Ordinal;
// DWORD AddressOfData; // PIMAGE_IMPORT_BY_NAME
// } u1;
// } IMAGE_THUNK_DATA32;
public class ImageThunkData32Bean {

    private boolean isByName;
    private DWord virtualAddress;// 该函数的虚拟地址
    private DWord medNameRelativeAddress;// 函数名称的相对地址

    private Word hint;
    private String asciiName;
    
    public Word getHint() {
        return hint;
    }


    
    public void setHint(Word hint) {
        this.hint = hint;
    }


    public DWord getMedNameRelativeAddress() {
        return medNameRelativeAddress;
    }

    
    public void setMedNameRelativeAddress(DWord medNameRelativeAddress) {
        this.medNameRelativeAddress = medNameRelativeAddress;
    }

    public DWord getVirtualAddress() {
        return virtualAddress;
    }

    public void setVirtualAddress(DWord virtualAddress) {
        this.virtualAddress = virtualAddress;
    }

    public boolean isByName() {
        return isByName;
    }

    public void setByName(boolean isByName) {
        this.isByName = isByName;
    }



    
    public String getAsciiName() {
        return asciiName;
    }



    
    public void setAsciiName(String asciiName) {
        this.asciiName = asciiName;
    }


}
