package com.olup.notable.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import com.olup.notable.db.Folder
import com.olup.notable.db.FolderRepository
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronRight
import com.olup.notable.utils.noRippleClickable

@Composable
fun BreadCrumb(folderId : String? = null, onSelectFolderId:(String?)->Unit){
    val context = LocalContext.current

    fun getFolderList (folderId : String) : List<Folder>{
        var folderList = listOf(FolderRepository(context).get(folderId))
        val parentId = folderList.first().parentFolderId
        if (parentId != null) {
            folderList += getFolderList(parentId)
        }
        return folderList
    }

    Row() {
        Text(text = "Library", textDecoration = TextDecoration.Underline,modifier = Modifier.noRippleClickable { onSelectFolderId(null) })
        if (folderId != null) {
            val folders = getFolderList(folderId).reversed()

            folders.map{ f->
                Icon(imageVector = FeatherIcons.ChevronRight, contentDescription = "")
                Text(text = f.title, textDecoration = TextDecoration.Underline, modifier = Modifier.noRippleClickable { onSelectFolderId(f.id) })
            }
        }
    }
}