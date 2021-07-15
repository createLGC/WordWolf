export default {
	template:`
		<div>
			<table border="1" style="border-collapse: collapse">
				<thead>
					<slot name="head"></slot>
				</thead>
				<tbody ref="body">
					<slot name="body"></slot>
					<tr v-if="isInputing" ref="textareaRow">
						<td v-for="n in cols" :key="n">
							<textarea
							cols="10"
							rows="1"
							style="resize:none"
							@keydown.enter.stop="addRow"
							:ref="setTextareaRef"
							></textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<p>
				<button type="button" @click="isInputing=true" v-show="!isInputing">追加</button>
				<button type="button" @click="reset" v-show="isInputing">キャンセル</button>
				<button type="button" @click="deleteRow">削除</button>
			</p>
		</div>
	`,
	data(){
		return {
			cols: 0,
			isInputing: false,
			textareaRefs: [] 
		}
	},
	mounted(){
		const body = this.$refs.body;
		this.cols = body.firstElementChild.children.length;
		Array.from(body.children).forEach(this.setRowOnClick);
	},
	methods:{
		setTextareaRef(el){
			if(el){
				this.textareaRefs.push(el);
			}
		},
		setRowOnClick(elem){
			elem.onclick = ()=>{
				Array.from(this.$refs.body.children).map(elem=>{
					elem.style.backgroundColor = "white";
					elem.style.color = "black";
					elem.selected = false;
				});
				elem.style.backgroundColor = "#777";
				elem.style.color = "white";
				elem.selected = true;
			};
		},
		addRow(){
			const tr = document.createElement('tr');
			this.textareaRefs.forEach(elem=>{
				const td = document.createElement('td');
				td.textContent = elem.value;
				tr.appendChild(td);
			});
			this.setRowOnClick(tr);
			this.$refs.body.insertBefore(tr, this.$refs.textareaRow);
			this.reset();
		},
		reset(){
			this.textareaRefs = [];
			this.isInputing = false;
		},
		deleteRow(){
			Array.from(this.$refs.body.children)
			.filter(elem=>elem.selected)
			.map(elem=>elem.remove());
		},
		getContents(){
			if(this.isInputing){
				this.reset();
			}
			return Array.from(this.$refs.body.children).map(
				tr=>Array.from(tr.children).map(
					td=>td.textContent.trim()
				)
			);
		}
	}
}